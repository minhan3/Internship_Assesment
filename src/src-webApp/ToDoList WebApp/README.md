# To‑Do List Web Application (Just Do It)

A simple, fast, client‑side task app. No backend and no build step — just open the HTML files in a browser.  
All data is saved to your browser’s **localStorage** so it works offline and persists across reloads.

---

## 1) How to Run

**Open locally** :

1. Double‑click `index.html` to open it in your browser.
2. Use the sidebar to navigate to **Today**, **Upcoming**, **Calendar**, and **Sticky Wall**.

> Tip: If you don’t see recent changes after editing files, try a hard refresh (Ctrl/Cmd+Shift+R).

---

## 2) Project Structure

```
/ (project root)
├─ index.html        # Today view (greeting + today's tasks)
├─ upcoming.html     # Upcoming (today, tomorrow, this week, next month)
├─ calendar.html     # Day view with hour rows + stacked bars per hour
├─ sticky.html       # Sticky wall (notes view)
├─ style.css         # Shared styles (layout, components, calendar bars, etc.)
├─ app.js            # All app logic (storage, rendering, events)
└─ JustDoIt.png      # Sidebar logo (optional)
```

> **Logo note:** If you want the logo at the bottom of the sidebar, put the image in `your directory/` and set  
> `src="your directory/JustDoIt.png"` in your HTML. Absolute file system paths (like `C:\Users\...`) will only work on your own machine.

---

## 3) Data Model & Storage

- All tasks are stored in browser localStorage under key: **`todo.tasks.v3`**.

```

Storage is per‑browser and per‑device. There is no cloud sync.

---

## 4) Features

- **Add / Edit / Delete / Complete** tasks.
- **Search** (matches title + description, live as you type).
- **Priority badges** (High / Medium / Low).
- **Counts** in the sidebar (Today, Upcoming, All, High, Done, Sticky).
- **Sticky Wall**: show selected tasks as colorful notes.
- **Calendar – Day view**:
  - Each hour row grows to fit all tasks at that hour (stacked vertically, no overlap).
  - Visible time window adapts to earliest & latest hours with tasks (e.g., a 19:00 task extends the view).
  - Pastel color per task (stable from its ID). Clicking a bar opens the edit drawer.
- **Today page**:
  - Shows tasks due **today** *and* tasks **without a date**.
  - Sorting: **not‑done first** → **higher priority** → **nearer due date** → **most recent**.
  - Minimal greeting (with Salam) + current date/time.
- **Upcoming page**: buckets for **Today**, **Tomorrow**, **This Week**, and **Next Month**.
- **Sidebar**:
  - Hide/Show with the top‑left ☰ button; a floating **☰ FAB** appears when hidden.
  - Active nav highlighting follows the current page.

---

## 5) Usage Notes

- **Time field** is optional. If set, tasks appear as bars in the Calendar at that hour.
- **No date** → the task appears on the **Today** page (for quick capture).
- **Calendar date picker** in the header changes the day you’re viewing.
- **Sticky** checkbox shows the task on the Sticky Wall in addition to the lists.
- **Search** filters the visible list on each page (title + description).

---

## 6) Known Limitations

- No multi‑day or duration support (each task is a single point in time or day).
- No drag‑and‑drop reordering or resizing on the calendar.
- localStorage is local to the browser/device (no cross‑device sync).

---

## 7) Implementation Notes (Where Things Live)

- **`app.js`** contains:
  - `renderToday`, `renderUpcoming`, `renderCalendar`, `renderSticky` — page renderers.
  - `renderCalendar()` computes dynamic hour heights and stacks bars vertically within each hour.
  - `counts()` updates the sidebar counters.
  - `updateGreeting()` prints the Salam + task count.
  - Drawer open/save/delete logic; search handling; sidebar toggle + floating FAB; active nav highlight.
- **`style.css`** defines the shell grid, cards, list items, badges, calendar bars, sticky notes, and responsive rules.

---

## 8) Credits / License

Made by **Yamin Nabhn**.  
You can adapt or extend this project for personal or educational use.
