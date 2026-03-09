#!/usr/bin/env python3
"""Enforce thin-client gateway-only boundary rules."""

from __future__ import annotations

import argparse
import pathlib
import re
import sys


ROOT = pathlib.Path(__file__).resolve().parents[1]
SOURCE_DIR = ROOT / "app" / "src" / "main"
APP_GRADLE = ROOT / "app" / "build.gradle.kts"
APP_MODULE = ROOT / "app" / "src" / "main" / "java" / "com" / "toreva" / "mobile" / "di" / "AppModule.kt"
APP_MODULE_REL = "app/src/main/java/com/toreva/mobile/di/AppModule.kt"

FORBIDDEN_ENDPOINT_PATTERNS = [
    r"localhost",
    r"127\.0\.0\.1",
    r"10\.0\.2\.2",
    r"\.internal\b",
    r"svc\.cluster\.local",
]
URL_PATTERN = re.compile(r"https?://[^\s\"')]+", re.IGNORECASE)
FORBIDDEN_PATTERN = re.compile("|".join(FORBIDDEN_ENDPOINT_PATTERNS), re.IGNORECASE)


def _iter_source_files() -> list[pathlib.Path]:
    files: list[pathlib.Path] = []
    for ext in ("*.kt", "*.kts", "*.java", "*.xml", "*.properties"):
        files.extend(SOURCE_DIR.rglob(ext))
    files.append(APP_GRADLE)
    return files


def _scan_for_forbidden_endpoints() -> list[str]:
    findings: list[str] = []
    for path in _iter_source_files():
        rel = path.relative_to(ROOT)
        text = path.read_text(encoding="utf-8", errors="ignore")
        for idx, line in enumerate(text.splitlines(), start=1):
            for match in URL_PATTERN.findall(line):
                if FORBIDDEN_PATTERN.search(match):
                    findings.append(f"{rel}:{idx} forbidden endpoint URL: {match}")
    return findings


def _check_baseurl_boundary() -> list[str]:
    findings: list[str] = []
    if not APP_MODULE.exists():
        # Fresh repos may not have DI wiring yet; do not fail boundary checks.
        return findings

    try:
        app_module = APP_MODULE.read_text(encoding="utf-8")
    except OSError as exc:
        findings.append(f"{APP_MODULE_REL} could not be read: {exc}")
        return findings

    if ".baseUrl(BuildConfig.TOREVA_API_URL)" not in app_module:
        findings.append(
            f"{APP_MODULE_REL} missing baseUrl(BuildConfig.TOREVA_API_URL)"
        )
    for idx, line in enumerate(app_module.splitlines(), start=1):
        if ".baseUrl(" in line and ("http://" in line or "https://" in line):
            findings.append(
                f"{APP_MODULE_REL}:{idx} hardcoded baseUrl detected"
            )
    return findings


def _self_test() -> bool:
    synthetic = "private val test = \"http://10.0.2.2:8080\""
    url = URL_PATTERN.search(synthetic)
    return bool(url and FORBIDDEN_PATTERN.search(url.group(0)))


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--self-test", action="store_true")
    args = parser.parse_args()

    if args.self_test:
        if _self_test():
            print("[boundary] OK: self-test violation was detected.")
            return 0
        print("[boundary] FAIL: self-test expected violation was not detected.", file=sys.stderr)
        return 1

    findings = []
    findings.extend(_scan_for_forbidden_endpoints())
    findings.extend(_check_baseurl_boundary())

    if findings:
        print("[boundary] FAIL: boundary violations detected:", file=sys.stderr)
        for finding in findings:
            print(f"- {finding}", file=sys.stderr)
        return 1

    print("[boundary] OK: gateway-only boundary checks passed.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
