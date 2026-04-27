# android fallback dispatch inbox

Primary transport is the coordinator message bus.
This folder is the repo-local intake queue only for pre-bus onboarding gaps or degraded-mode fallback when coordinator bus delivery or GitHub relay is unavailable.

Required headers for new files:
- `Status:` staged | acknowledged | in_progress | completed | rejected | superseded | expired
- `Fallback reason:` pre_bus_gap | degraded_mode
- `From:` source repo or actor
- `To:` target repo or accountable
- `Priority:` P0 | P1 | P2 | P3
- `Raised:` ISO date or timestamp

Update status in place as work progresses. Coordinator aggregates this queue in `reports/dispatch/fallback-dispatch-summary.md`.
