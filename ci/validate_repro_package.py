#!/usr/bin/env python3
"""Validate reproducibility package JSON against schema."""

from __future__ import annotations

import argparse
import json
import os
import sys
import urllib.request

try:
    import jsonschema
except Exception as exc:  # pragma: no cover - import guard
    print(f"[repro] jsonschema import failed: {exc}", file=sys.stderr)
    print("[repro] Install with: pip install jsonschema", file=sys.stderr)
    sys.exit(2)


def _load_json(path: str) -> dict:
    with open(path, "r", encoding="utf-8") as handle:
        return json.load(handle)


def _validate(instance: dict, schema: dict, label: str) -> None:
    jsonschema.validate(instance=instance, schema=schema)
    print(f"[repro] OK: package validates against {label}")


def _load_remote_schema(url: str) -> dict:
    with urllib.request.urlopen(url, timeout=20) as resp:
        payload = resp.read().decode("utf-8")
    return json.loads(payload)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--package",
        default=os.environ.get(
            "REPRO_PACKAGE_PATH",
            "compliance/reproducibility/package.example.json",
        ),
    )
    parser.add_argument(
        "--schema",
        default=os.environ.get(
            "REPRO_SCHEMA_PATH",
            "compliance/reproducibility/reproducibility-package.schema.json",
        ),
    )
    parser.add_argument(
        "--remote-schema-url",
        default=os.environ.get("REPRO_SCHEMA_URL", ""),
    )
    parser.add_argument(
        "--require-remote-schema",
        action="store_true",
        default=os.environ.get("REPRO_REQUIRE_REMOTE_SCHEMA", "false").lower() == "true",
    )
    args = parser.parse_args()

    try:
        package_data = _load_json(args.package)
        local_schema = _load_json(args.schema)
        _validate(package_data, local_schema, f"local schema: {args.schema}")
    except Exception as exc:
        print(f"[repro] FAIL: local schema validation error: {exc}", file=sys.stderr)
        return 1

    if not args.remote_schema_url:
        print("[repro] No remote schema URL configured; local schema validation only.")
        return 0

    try:
        remote_schema = _load_remote_schema(args.remote_schema_url)
        _validate(package_data, remote_schema, f"remote schema: {args.remote_schema_url}")
        return 0
    except Exception as exc:
        if args.require_remote_schema:
            print(
                f"[repro] FAIL: remote schema validation required but failed: {exc}",
                file=sys.stderr,
            )
            return 1
        print(f"[repro] WARN: remote schema validation skipped due to error: {exc}")
        return 0


if __name__ == "__main__":
    raise SystemExit(main())
