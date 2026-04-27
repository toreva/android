# Agent Operating Context — android-mobile

## Purpose
Native Android app for the Toreva platform. Hackathon submission for the
Solana Mobile Hackathon. Connects to the Toreva API gateway to provide
wallet-based authentication, vault creation, strategy activation, and
receipt display with on-chain verification.

## Governance Acknowledgment
This repository acknowledges and operates under Thin Client Build Constitution
`CON-BUILD-001`. This repo owner is the local builder and DevSecOps owner for
`android_shadow`; the Coordinator governs policy and conformance but is not the
build executor for this repo.

## Architecture Role
Thin client. All business logic is server-side. This app:
- Connects wallet via MWA (Solana Mobile Stack)
- Authenticates via gateway-issued JWT
- Submits intents and displays receipts
- Opens Solscan for on-chain verification

## Connections
- Toreva API Gateway (BuildConfig.TOREVA_API_URL) — all API calls
- Solana RPC (BuildConfig.RPC_URL) — vault creation tx submission only
- Phantom/Solflare wallet app — via Mobile Wallet Adapter

## Boundaries
This repo MUST NOT:
- Connect to any backend service directly
- Contain strategy, execution, or financial logic
- Store private keys or seed phrases
- Reference internal infrastructure (repo names, service URLs, DB schemas)

## Delivery Model
- Thin-client only, platform-native Android implementation
- Gateway-only backend access (single enterprise API surface)
- Local DevSecOps ownership for CI/build/release controls in this repo
- Delayed public mirror model: public/mirror release happens after internal
  validation evidence is produced (reproducibility package, boundary checks,
  security gates, SBOM/provenance/signature references)

## Design Policy
- Read `docs/DESIGN_CONSTITUTION.md` before any UI/UX changes.
- Enforce `docs/ANDROID_DESIGN_ENFORCEMENT.md` for Android visual behavior.
- If design policy conflicts exist, `docs/DESIGN_CONSTITUTION.md` takes
  precedence.

## Build
gradle assembleDebug

## Test
gradle test

<!-- TOREVA-FALLBACK-DISPATCH START -->
## Dispatch Inbox

At the start of every session, check `intake/pending-dispatches/` for repo-local work packets.
Primary transport is still the coordinator message bus.
Use this folder only as the pre-bus or degraded-mode fallback when a repo is not yet fully bus-onboarded, or when bus/GitHub relay delivery is unavailable.
Update the dispatch file status in place when you acknowledge, progress, complete, reject, supersede, or expire the work.
Cross-repo queue visibility is aggregated in `coordinator/reports/dispatch/fallback-dispatch-summary.md`.
<!-- TOREVA-FALLBACK-DISPATCH END -->
