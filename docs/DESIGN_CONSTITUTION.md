# DESIGN CONSTITUTION

This document is the top-level authority for design and visual behavior in this
repository.

## Order of precedence

1. This `DESIGN_CONSTITUTION.md`
2. `docs/ANDROID_DESIGN_ENFORCEMENT.md`
3. Feature-level implementation details

When conflicts exist, higher precedence wins.

## Core rule

No design invention is allowed. Implementations must follow documented tokens,
component contracts, and screen definitions exactly.

## Android enforcement binding

Android-related visual work must follow
`docs/ANDROID_DESIGN_ENFORCEMENT.md` in full, including:

- exact color, typography, spacing, and component specs
- WebView parity rules
- Capacitor shell requirements
- explicit "must not" constraints
- verification checklist before shipping
