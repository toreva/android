# Toreva Android Mobile

## 1) What is Toreva
Toreva is an autonomous DeFi execution engine for Solana Mobile. This app is a native Android thin client for wallet authentication, vault creation, strategy activation, and receipt verification.

## 2) Architecture
`android-mobile app -> Toreva gateway API -> Solana + private backend systems`

## Governance + Operating Model
- Thin-client Android app; no strategy/execution/financial engine logic lives in this repo.
- Gateway-only backend access through `BuildConfig.TOREVA_API_URL`.
- Local DevSecOps ownership: this repo owns build, security, and release gates.
- Delayed public mirror model: internal validation/evidence is generated before public mirror/release sync.

## 3) Screenshots
To be added after first device build and run.

## 4) Requirements
- Android 8.0+ (API 26)
- Phantom or Solflare wallet app with Mobile Wallet Adapter support

## 5) Build
1. Copy `local.properties.example` values to `local.properties`
2. Run `gradle assembleDebug`

## CI Compliance Gates
- `reproducibility/package`: reproducibility package schema validation gate
- `ci/boundary`: gateway-only boundary checks (fails on internal endpoint usage)
- `ci/lint`: Android lint
- `ci/typecheck`: Kotlin compile/typecheck
- `ci/unit`: JVM unit tests
- `security/secrets`: repository secret scan
- `ci/build`: debug build (blocked on all gates above)

Release evidence hooks run in `.github/workflows/release-evidence.yml` and publish
SBOM, provenance attestation, and signature-reference artifacts.

## 6) Install
`adb install app/build/outputs/apk/debug/app-debug.apk`

## 7) Hackathon criteria mapping
- Solana Mobile Stack: Mobile Wallet Adapter flow for authorize/sign message/sign transaction
- Mobile-first: Kotlin + Jetpack Compose native app
- Meaningful Solana interaction: signed vault creation transaction + verifiable receipt signatures

## 8) LIVE vs PREVIEW
USDC Yield is LIVE for activation. SOL Staking and Meme Rebalance are PREVIEW and marked coming soon.

## 9) Tech stack
Kotlin, Jetpack Compose Material3, Hilt, Retrofit + Moshi, DataStore, Coroutines, Solana MWA, sol4k.

## 10) License
TBD
