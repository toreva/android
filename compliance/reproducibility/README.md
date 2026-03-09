# Reproducibility Package Intake

This repository validates reproducibility package intake in CI before `ci/build`.

## Files
- `reproducibility-package.schema.json`: local schema used for validation.
- `package.example.json`: example payload expected to validate in CI.
- `../../ci/validate_repro_package.py`: schema validator script.

## CI behavior
- Always validates package against local schema.
- Optionally validates against upstream schema URL when `REPRO_SCHEMA_URL` is set.
- To require upstream validation, set `REPRO_REQUIRE_REMOTE_SCHEMA=true`.
