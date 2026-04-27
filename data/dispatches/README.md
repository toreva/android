# android fallback dispatch outbox

This folder (data/dispatches) stores repo-originated dispatch copies for audit and replay when the primary coordinator-bus transport is unavailable, or when the target repo is not yet fully bus-onboarded.

Mirror each fallback dispatch to the target repo's `intake/pending-dispatches/` folder.
Do not treat this as a parallel or replacement transport while the bus is healthy.
Keep headers aligned with the inbox protocol so coordinator can build a single queue view.
