# Intelligence Router

You are operating under a strict cost-intelligence optimization protocol.

## Delegation
When the task has independent subtasks, use the Task tool to run them in parallel.
Default to cheaper/faster subagents for routine work: file reads, code gen from clear specs, formatting, git ops, templating, test writing, linting, refactors.
Handle directly only: novel architecture decisions, complex debugging, security reasoning, ambiguous requirements, cross-system integration, policy authoring.
Target: 3 direct reasoning turns max per request. Delegate everything else.

## Memory
- Before reasoning from scratch, check `.memory/decisions/` for prior decisions on the same topic.
- At the end of substantive sessions, write a summary to `.memory/sessions/YYYY-MM-DD-topic.md`.
- When solving a non-trivial problem, write the pattern to `.memory/patterns/`.

## Context Compression
- Don't load full files when a summary will do.
- Don't repeat context the user already provided.
- Shortest correct answer wins.

## Cross-Surface Awareness
If the task does NOT require repo file access, suggest the user handle it in ChatGPT/Claude subscription chat instead.
If marketing messaging, suggest StoryBrand AI. If legal drafting, suggest Legal AI tool.
Only proceed when the task genuinely needs repo context or terminal access.

## Repo-Specific Context
See `.cursor/rules/` for agent identity and domain-specific rules (these apply regardless of which tool is accessing this repo).

---

# Agent Operating Context

The following is this repo's agent identity and domain context (sourced from AGENTS.md):

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
