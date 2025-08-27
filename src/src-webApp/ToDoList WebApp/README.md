# To-Do List Web Application (Just Do It)

A simple, fast, and fully client-side task app. No backend, no build step—just open the files in a browser. Tasks are saved to `localStorage`.

---

1) HOW TO RUN THE WEB APPLICATION

• Open directly
 1. Double-click `index.html` to open it in your browser.
 2. Navigate via the sidebar to **Upcoming**, **Calendar**, and **Sticky Wall**.

> Tip: Chrome/Edge/Safari all work. If anything doesn’t render after edits, hard-refresh (Ctrl/Cmd+Shift+R).

2) PROJECT STRUCTURE
   /index.html       # Today view
   /upcoming.html    # Upcoming (today, tomorrow, week, next month)
   /calendar.html    # Day view with hour rows & stacked tasks per hour
   /sticky.html      # Sticky notes wall
   /style.css        # Shared styles
   /app.js           # All app logic (storage, rendering, events)
   /JustDoIt.png     # images

3) DATA MODEL & STORAGE
   All tasks are stored in localStorage under key: todo.tasks.v3.

4) FEATURES IMPLEMENTED
   Core: • Add / Edit / Delete / Complete tasks across all pages.
         • Search (title + description, live as you type).
         • Priority badges (High/Medium/Low).
         • Counts in the sidebar (Today, Upcoming, All, High, Done, Sticky).
         • LocalStorage persistence—everything survives refresh and page changes.

  index.html: • Minimal greeting with time-of-day flavor + today’s task count.
              • Current date/time shown under the greeting.
              • Today list shows tasks due today or with no due date.
              • Sorting: not-done first → higher priority → nearer due date → most recent.

  upcoming.html: • Buckets for Today, Tomorrow, This Week, and Next Month.
                 • Uses the same task card style for visual consistency.

  calendar.html: • Day view with hour rows.
                 • Multiple tasks at the same hour stack vertically in the same row (no overlap with other hours).
                 • Dynamic day height: the earliest/ latest hour with tasks determines the visible range (e.g., a task at
                   19:00 extends below 17:00).
                 • Pastel color per task (stable hash by ID), with matching border.
                 • Clicking a bar opens the edit drawer.

  sticky.html: • Shows tasks with sticky: true as colored notes.

  Sidebar & layout: • Hide/show sidebar with state persistence and a floating ☰ FAB to reopen.
                  • Active nav highlighting matches the current page.
                  • Bottom-left logo area in the sidebar footer (.sb-foot), sized & muted by default.

5) USAGE NOTES
 • Time field is optional. If omitted, the Calendar places the task at 09:00 by default.
 • Today page: If you create a task here without a date, it is considered a “today” task.
 • Calendar page: If you add a task here without a date, it defaults to the currently selected day.
 • Sticky toggle puts the task on the Sticky Wall in addition to normal lists.

6) KNOWN LIMITATIONS
 • No multi-day or duration support (each task is a single point in time or date).
 • No drag-and-drop reordering or resizing on the calendar.
 • LocalStorage is per-browser and per-device (no sync).

7) LICENSE/CREDITS
   Made by Yamin Nabhan






















