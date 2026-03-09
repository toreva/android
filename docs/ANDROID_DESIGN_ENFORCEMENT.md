# ANDROID DESIGN ENFORCEMENT SPEC

Purpose: The Android build (Capacitor WebView) MUST render pixel-identical to
the web app at `https://gobln.app/day1`. This document defines every visual
parameter. No invention. No deviation. No "improvements."

Authority: Subordinate to `docs/DESIGN_CONSTITUTION.md`. If there is a
conflict, the Design Constitution wins.

---

## 0) The Rule

The Android app is a WebView. It renders the same React component tree as the
web app. There is no separate Android UI. If the Android app looks different
from the web app, the Android shell is broken, not the web app.

What this means for agents:

- Do NOT create Android-specific components.
- Do NOT create Android-specific stylesheets.
- Do NOT add platform detection to change layouts.
- Do NOT override any CSS or design token for Android.
- The ONLY Android-specific config lives in `capacitor.config.ts` and
  `android/`.

---

## 1) Color Tokens (Exact Hex, No Substitution)

| Token | Hex | Usage |
|-------|-----|-------|
| BACKGROUND | `#0A0A0A` | App canvas, html/body background |
| BACKGROUND_CARD | `#141414` | Card surfaces |
| BACKGROUND_ELEVATED | `#1E1E1E` | Sheets, modals, elevated surfaces |
| BORDER | `#2A2A2A` | Dividers, card edges |
| TEXT_PRIMARY | `#FFFFFF` | Headlines, body text |
| TEXT_SECONDARY | `#A0A0A0` | Descriptions, secondary copy |
| TEXT_MUTED | `#666666` | Captions, timestamps |
| PRIMARY_TEAL | `#008080` | Trust, success, data emphasis |
| SECONDARY_ORANGE | `#FF7A3C` | Primary CTA fill only |
| ERROR | `#722F37` | Critical alerts only |
| NEUTRAL_GREY | `#404040` | Muted UI elements |

Source of truth: `DAY1_COLORS` in `lib/day1-constants.ts`.

Light mode does not exist. Dark is the only theme.

---

## 2) Typography (Exact Values)

Font family: Space Grotesk (sans), Space Mono (monospace/numeric).

| Style | Size (px) | Weight | Line Height | Usage |
|-------|-----------|--------|-------------|-------|
| caption2 | 11 | 400 | 1.3 | Nav labels, micro badges |
| caption | 12 | 400 | 1.35 | Tags, timestamps |
| footnote | 13 | 400 | 1.4 | Secondary text |
| body | 14 | 400 | 1.45 | Body text, descriptions |
| callout | 16 | 500 | 1.4 | Card titles, labels |
| headline | 18 | 600 | 1.35 | Sheet titles, section headers |
| title3 | 20 | 600 | 1.3 | Medium hero text |
| title2 | 24 | 700 | 1.25 | Large headings |
| title1 | 28 | 700 | 1.2 | Hero headings |

Source of truth: `DAY1_TYPOGRAPHY` in `lib/day1-constants.ts`.

---

## 3) Spacing (8pt Grid, Exact px Values)

| Token | px |
|-------|----|
| xxs | 4 |
| xs | 8 |
| sm | 12 |
| md | 16 |
| lg | 20 |
| xl | 24 |
| xxl | 32 |
| screenX (horizontal padding) | 20 |
| screenBottom (above nav) | 100 |

Source of truth: `DAY1_SPACING` in `lib/day1-constants.ts`.

---

## 4) Component Specs (Exact)

### Day1Button
- Primary: `#FF7A3C` fill, white text, pill shape (`border-radius: 9999px`)
- Heights: sm=36px, md=44px, lg=56px
- Full width + size `lg` for all primary CTAs in onboarding/sheets
- Min touch target: 44px

### Day1Card
- Background: `#141414`
- Border: `1px solid #2A2A2A`
- Border radius: 16px
- Padding: 16px or 20px

### Day1Sheet (bottom-up popup)
- Background: `#1E1E1E`
- Top handle: 4px x 40px, `#404040`, centered
- Border radius (top): 20px
- Shadow: `0 -4px 24px rgba(0, 0, 0, 0.35)`
- Max width: 480px

### BottomNavDay1
- Three icons: Act (Inbox), View (Activity), Fund (Wallet)
- Active: `#FFFFFF` icon + 2px bottom indicator
- Inactive: `#666666` icon
- Height: 56px content + safe area
- Icon-only, no labels

### TopBar
- Menu (left), Title (center), Profile (right)
- Button: 44px circle, `#141414` background
- Title: 18px, weight 600, `#FFFFFF`

---

## 5) Screen Journey (Canonical Order)

### Onboarding (full-screen steps)
1. Splash - Animated toreva sigil (~2.8s)
2. Welcome - Logo 160px, "An autopilot for your money", orange CTA "Get started"
3. Disclosures - Shield icon, three bullets, "I Understand" CTA
4. Passkey - "Enable Passkey" -> success animation -> auto-advance
5. Explore Gate - "You're ready to explore", "Let's explore ->"

### In-Life (tabbed app)
- Act tab - Hero status card, readiness CTA, single WHEN/WHO recommendation
- View tab - Receipt feed (proof-only)
- Align tab - Chat input, agent, W5H cards
- Fund tab - Wallet/Vault/Fuel cards, "Get started" links

### Setup Sheet Chain (bottom-up popups over in-life app)
`wallet-setup -> backup -> strategy-selection`
(Extended when ENABLE_VAULT_FLOW=true: wallet -> backup -> vault -> fuel ->
funds -> strategy)

---

## 6) Capacitor Shell Requirements

`capacitor.config.ts` exact values:

```typescript
const config: CapacitorConfig = {
  appId: "app.gobln.toreva",
  appName: "toreva",
  webDir: "out",
  server: {
    androidScheme: "https",
    cleartext: false,
  },
  plugins: {
    StatusBar: {
      style: "DARK",
      backgroundColor: "#0A0A0A",
    },
    SplashScreen: {
      launchAutoHide: true,
    },
  },
};
```

Android Status Bar:
- Background: `#0A0A0A`
- Style: DARK (white icons)
- No light status bar

WebView:
- Must load `out/` directory (from `NEXT_OUTPUT=export npm run build`)
- Must support `https` scheme
- Must respect `env(safe-area-inset-*)` CSS

---

## 7) What Agents Must NOT Do

1. Do NOT create Android-specific layouts, components, or styles.
2. Do NOT add `Platform.OS` checks or user-agent sniffing to change UI.
3. Do NOT substitute or approximate color values.
4. Do NOT change typography or spacing values.
5. Do NOT add new screens outside `lib/screen-registry.ts`.
6. Do NOT move/reorder/add navigation items (three tabs only).
7. Do NOT use Tailwind classes for new colors/spacing. Use constants.
8. Do NOT implement light mode.
9. Do NOT add visual effects not specified in this doc.
10. Do NOT change Capacitor config beyond section 6.

---

## 8) Verification Checklist

Before any Android-related PR:

- [ ] `capacitor.config.ts` has `webDir: "out"` and `cleartext: false`
- [ ] Status bar is `#0A0A0A` and style `DARK`
- [ ] Build uses `NEXT_OUTPUT=export npm run build` (outputs `out/`)
- [ ] No Android-specific CSS or component files added
- [ ] All colors reference `DAY1_COLORS`
- [ ] All spacing references `DAY1_SPACING`
- [ ] All typography references `DAY1_TYPOGRAPHY`
- [ ] No new screens or navigation items added
- [ ] Android app loads `/day1`
- [ ] Android screenshots match web screenshots at 360px width

---

## Agent Prompt: Android Build Compliance

Use this verbatim prompt when instructing an agent to work on Android build,
Capacitor config, or any mobile-related task.

```text
You are working on the Android build of the toreva app (goblin_ui repo). The Android app is a Capacitor WebView that renders the exact same React component tree as the web app. There is NO separate Android UI.

MANDATORY READING - Read these files IN THIS ORDER before making any change:
1. docs/DESIGN_CONSTITUTION.md
2. docs/ANDROID_DESIGN_ENFORCEMENT.md
3. lib/day1-constants.ts (DAY1_COLORS, DAY1_SPACING, DAY1_TYPOGRAPHY, DAY1_RADIUS)
4. lib/screen-registry.ts
5. capacitor.config.ts

ABSOLUTE CONSTRAINTS - Violating any of these is a rejected PR:

1. NO INVENTION. You must not create any component, screen, stylesheet, layout, color, spacing value, or interaction that does not already exist in the codebase or is not specified in the design constitution and enforcement spec.

2. NO ANDROID-SPECIFIC UI. Do not create Android-specific components, styles, layouts, or platform checks. The WebView renders the web app. If it looks wrong on Android, the Capacitor shell config is the problem - not the React components.

3. EXACT DESIGN TOKENS. Every color must come from DAY1_COLORS. Every spacing value from DAY1_SPACING. Every font size/weight from DAY1_TYPOGRAPHY. Every radius from DAY1_RADIUS. No hardcoded values. No approximations. No close enough.

4. NO NEW SCREENS. The screen inventory is defined in lib/screen-registry.ts. Do not add screens. Do not remove screens. Do not rename screens.

5. NO NAVIGATION CHANGES. Three bottom tabs: Act, View, Fund. Do not add tabs. Do not remove tabs. Do not reorder tabs.

6. NO LIGHT MODE. Dark is the only shipping theme. Background is #0A0A0A. Do not implement, mention, or prepare for light mode.

7. STATIC EXPORT ONLY. The Android build uses NEXT_OUTPUT=export npm run build which produces an out/ directory. capacitor.config.ts must have webDir: out. The .next/ directory is NOT for Capacitor.

8. STATUS BAR. Android status bar must be: backgroundColor #0A0A0A, style DARK. These are set in capacitor.config.ts plugins.StatusBar.

WHEN DEBUGGING VISUAL DIFFERENCES between Android and web:
- First verify capacitor.config.ts is correct (webDir: out, StatusBar config)
- Then verify the build was done with NEXT_OUTPUT=export
- Then verify android/app/src/main/assets contains the static export files
- Then check WebView settings in android/ Java/Kotlin files
- Do NOT modify React components to fix Android-specific rendering issues

WHEN ASKED TO MAKE IT LOOK LIKE a reference:
- Do NOT invent UI. Compare the reference against the existing components in the codebase.
- If the existing component matches the reference, the problem is the Capacitor shell, not the component.
- If the existing component does not match the reference, update it using ONLY design tokens from day1-constants.ts.

OUTPUT FORMAT:
- State which files you read before making changes
- State which design tokens you are using and why
- State which screen from screen-registry.ts you are modifying (if any)
- Confirm no Android-specific code was added
```
