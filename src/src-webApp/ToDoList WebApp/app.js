// To-Do app (localStorage). Manages data, renders each view, and wires UI events.

const STORAGE_KEY = "todo.tasks.v3";

/** In-memory task list loaded from localStorage */
let tasks = JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]");

/** Editing state + search text */
let currentId = null;
let searchText = "";

/* query shortcuts */
const qs = sel => document.querySelector(sel);
const qsa = sel => Array.from(document.querySelectorAll(sel));
const byId = id => document.getElementById(id);

/* ids, storage, formatting */
const uid = () => (crypto.randomUUID ? crypto.randomUUID() : String(Date.now()+Math.random()));
const save = () => localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks));
const todayISO = () => new Date().toISOString().slice(0,10);
const cap = s => s[0].toUpperCase() + s.slice(1);
const esc = s => (s||"").replace(/[&<>"']/g,c=>({ "&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;" }[c]));

/* ---------- nav highlight (based on current page) ---------- */
function setActiveNav() {
  const page = (location.pathname.split('/').pop() || 'index.html').toLowerCase();
  qsa('aside .nav a.navlink').forEach(a => {
    const href = (a.getAttribute('href') || '').toLowerCase();
    a.classList.toggle('active', href === page);
  });
}

/* ---------- header date/time text ---------- */
function fmtHeader() {
  const el = byId("pageSub");
  if (!el) return;
  const d = new Date();
  el.textContent = new Intl.DateTimeFormat(undefined, {
    weekday:"long", day:"2-digit", month:"long", year:"numeric",
    hour:"2-digit", minute:"2-digit"
  }).format(d);
}

/* ---------- sidebar counts ---------- */
function counts() {
  const map = {
    "count-today":    tasks.filter(t => t.due === todayISO()).length,
    "count-upcoming": tasks.filter(t => t.due && t.due >= todayISO()).length,
    "count-all":      tasks.length,
    "count-high":     tasks.filter(t => t.priority === "high" && !t.done).length,
    "count-done":     tasks.filter(t => t.done).length,
    "count-sticky":   tasks.filter(t => t.sticky).length,
  };
  Object.entries(map).forEach(([id,val]) => {
    const el = byId(id); if (el) el.textContent = val;
  });
}

/* ---------- list helpers (sort, search match, badge) ---------- */
function sortTasks(arr){
  const p = {high:3, medium:2, low:1};
  return [...arr].sort((a,b)=>{
    if (a.done !== b.done) return a.done ? 1 : -1;                 // unfinished first
    const pp = p[b.priority] - p[a.priority]; if (pp) return pp;    // higher priority first
    if (a.due && b.due && a.due !== b.due) return a.due.localeCompare(b.due);
    return b.createdAt - a.createdAt;                               // newest first
  });
}
function matchesQuery(t,q){
  if(!q) return true;
  q=q.toLowerCase();
  return t.title.toLowerCase().includes(q) || (t.desc||"").toLowerCase().includes(q);
}
const badge = (cls,txt)=>`<span class="badge ${cls}">${txt}</span>`;

/* ---------- list row (Today/Upcoming/Sticky share this look) ---------- */
function row(t){
  return `
    <article class="item ${t.done?"status-done":""}" data-id="${t.id}">
      <div><input type="checkbox" class="checkbox" ${t.done?"checked":""}></div>
      <div>
        <h3 class="title">${esc(t.title)}</h3>
        <p class="desc">${esc(t.desc)}</p>
        <div class="meta">
          ${badge("priority-"+t.priority,"Priority: "+cap(t.priority))}
          ${t.due ? badge("badge-date","Due: "+t.due) : ""}
          ${t.sticky ? badge("","Sticky") : ""}
        </div>
      </div>
      <div class="controls">
        <button class="icon edit" title="Edit" aria-label="Edit task">
          <svg viewBox="0 0 24 24" aria-hidden="true">
            <path d="M12 20h9"></path>
            <path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4Z"></path>
          </svg>
        </button>
        <button class="icon del" title="Delete" aria-label="Delete task">
          <svg viewBox="0 0 24 24" aria-hidden="true">
            <polyline points="3 6 5 6 21 6"></polyline>
            <path d="M19 6l-1 14a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2L5 6"></path>
            <path d="M9 6V4a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v2"></path>
            <path d="M10 11v6"></path>
            <path d="M14 11v6"></path>
          </svg>
        </button>
      </div>
    </article>`;
}

/* ---------- greeting on Today (time-of-day + task count) ---------- */
function updateGreeting() {
  const el = byId("greeting");
  if (!el) return;

  const h = new Date().getHours();
  const part = h < 12 ? "morning" : h < 18 ? "afternoon" : "evening";

  // Count items visible on Today (due today or no date)
  const count = tasks.filter(t => !t.due || t.due === todayISO()).length;
  const nfmt = new Intl.NumberFormat().format(count);
  const plural = count === 1 ? "" : "s";

  el.textContent = `Assalamu’alaikum, Yamin! Ready to get started? You have ${nfmt} task${plural} today.`;
}

/* ---------- Renders: Today ---------- */
function renderToday(){
  const list = byId("task-list"); if (!list) return;
  const q = searchText.trim().toLowerCase();
  const base = q ? tasks : tasks.filter(t => (!t.due || t.due === todayISO()));
  const view = sortTasks(base.filter(t=>matchesQuery(t,q)));
  list.innerHTML = view.length ? view.map(row).join("") : `<div class="empty">No tasks for today.</div>`;
}

/* ---------- Renders: Upcoming (split into groups) ---------- */
function renderUpcoming(){
  const tdy = todayISO();
  const tomorrow = new Date(Date.now()+86400000).toISOString().slice(0,10);

  const weekEnd = new Date();
  weekEnd.setDate(weekEnd.getDate()+7);
  const weekISO = weekEnd.toISOString().slice(0,10);

  // Calculate first/last day of the next month
  const now = new Date();
  const firstNextMonth = new Date(now.getFullYear(), now.getMonth()+1, 1);
  const firstNextISO   = firstNextMonth.toISOString().slice(0,10);
  const lastNextMonth  = new Date(now.getFullYear(), now.getMonth()+2, 0);
  const lastNextISO    = lastNextMonth.toISOString().slice(0,10);

  const q = searchText.trim().toLowerCase();
  const put = (id,data) => {
    const el = byId(id); if (!el) return;
    const v = sortTasks(data.filter(t=>matchesQuery(t,q)));
    el.innerHTML = v.length ? v.map(row).join("") : `<div class="empty">Empty</div>`;
  };

  put("upcoming-today",    tasks.filter(t=> t.due===tdy));
  put("upcoming-tomorrow", tasks.filter(t=> t.due===tomorrow));
  put("upcoming-week",     tasks.filter(t=> t.due && t.due>tomorrow && t.due<=weekISO));
  put("upcoming-next-month", tasks.filter(t=>{
    if (!t.due) return false;
    return t.due >= firstNextISO && t.due <= lastNextISO;
  }));
}

/* ---------- Renders: Calendar (stack tasks per hour; auto-height) ---------- */
function renderCalendar(){
  const hoursEl = byId("calHours"), tracksEl = byId("calTracks");
  if (!hoursEl || !tracksEl) return;

  // Set day title
  const calDate = byId("calDate");
  const day = calDate?.value || todayISO();
  const titleEl = byId("calTitle");
  if (titleEl){
    titleEl.textContent = new Intl.DateTimeFormat(undefined,{day:"2-digit",month:"long",year:"numeric"}).format(new Date(day));
  }

  // Layout constants (tuned to CSS)
  const DEFAULT_START = 9;     // earliest hour to show
  const DEFAULT_END   = 18;    // last visible boundary (exclusive)
  const BASE_HH = 56;          // base hour row height
  const PAD_TB  = 8;           // inner top/bottom padding
  const BAR_H   = 36;          // height of each bar
  const V_GAP   = 6;           // space between stacked bars

  // Extract hour (fallback 09:00 when missing)
  const hourOf = (t) => {
    const s = t.dueTime || "09:00";
    const h = parseInt(s.slice(0,2), 10);
    return Number.isFinite(h) ? h : 9;
  };

  // Pick tasks for selected day
  const dayTasks = sortTasks(tasks.filter(t => t.due === day));

  // Compute window [start,end) so late tasks expand view
  let minHour = DEFAULT_START, maxHour = DEFAULT_END - 1;
  if (dayTasks.length){
    const hrs = dayTasks.map(hourOf);
    minHour = Math.min(DEFAULT_START, ...hrs);
    maxHour = Math.max(DEFAULT_END - 1, ...hrs);
  }
  const HOUR_START = Math.max(0, Math.min(23, minHour));
  const HOUR_END   = Math.max(HOUR_START + 1, Math.min(24, maxHour + 1)); // exclusive

  // Group tasks by hour
  const byHour = new Map();
  for (let h = HOUR_START; h < HOUR_END; h++) byHour.set(h, []);
  dayTasks.forEach(t => {
    const h = Math.min(Math.max(hourOf(t), HOUR_START), HOUR_END - 1);
    byHour.get(h).push(t);
  });

  // Row height per hour = padding + bars + gaps (at least BASE_HH)
  const hourHeights = [];
  for (let h = HOUR_START; h < HOUR_END; h++){
    const n = byHour.get(h).length;
    const need = PAD_TB*2 + (n>0 ? (n * BAR_H + (n - 1) * V_GAP) : BAR_H);
    hourHeights.push(Math.max(BASE_HH, need));
  }

  // Render hour labels with variable heights
  const labels = [];
  for (let i = 0; i < hourHeights.length; i++){
    const label = String(HOUR_START + i).padStart(2,"0") + ":00";
    labels.push(`<div class="hour" style="height:${hourHeights[i]}px">${label}</div>`);
  }
  hoursEl.innerHTML = labels.join("");

  // Place task bars stacked within their hour block
  tracksEl.innerHTML = "";
  let cumTop = 0;
  for (let i = 0; i < hourHeights.length; i++){
    const h = HOUR_START + i;
    const items = byHour.get(h);
    const baseTop = cumTop + PAD_TB;

    items.forEach((t, j) => {
      // Stable pastel color from id
      let seed = 0; for (let k=0;k<t.id.length;k++) seed = (seed*31 + t.id.charCodeAt(k)) >>> 0;
      const hue = seed % 360;
      const bg = `hsl(${hue} 100% 92%)`;
      const bd = `hsl(${hue} 70% 75%)`;

      const bar = document.createElement("div");
      bar.className = "bar";
      bar.dataset.id = t.id;

      bar.style.top = `${baseTop + j * (BAR_H + V_GAP)}px`;
      bar.style.height = `${BAR_H}px`;
      bar.style.background = bg;
      bar.style.borderColor = bd;

      bar.innerHTML = `
        <span class="title">${esc(t.title)}</span>
        ${t.priority ? `<span class="badge priority-${t.priority}" style="margin-left:8px">Priority: ${cap(t.priority)}</span>` : ""}
      `.trim();

      bar.addEventListener("click", () => openDrawer(t));
      tracksEl.appendChild(bar);
    });

    cumTop += hourHeights[i];
  }

  // Ensure the scroll area is tall enough for all rows
  tracksEl.style.height = `${hourHeights.reduce((a,b)=>a+b,0)}px`;
}

/* ---------- Renders: Sticky wall ---------- */
function renderSticky(){
  const grid = byId("sticky-grid"); if (!grid) return;
  const colors = ["yellow","blue","pink","gray"];
  const q = searchText.trim().toLowerCase();
  const cards = sortTasks(tasks.filter(t=>t.sticky && matchesQuery(t,q))).map((t,i)=>`
    <article class="note ${colors[i%colors.length]}" data-id="${t.id}">
      <h3 class="title">${esc(t.title)}</h3>
      <p class="desc">${esc(t.desc)}</p>
      <div class="meta">${t.due ? badge("badge-date","Due: "+t.due) : ""}</div>
    </article>
  `);
  grid.innerHTML = cards.join("") || `<div class="empty">No sticky notes. Toggle “Show on Sticky Wall” in a task.</div>`;
}

/* ---------- Drawer open/close + form submit ---------- */
function openDrawer(t){
  const f = id => byId(id);
  if (t){
    f("title").value = t.title;
    f("desc").value  = t.desc || "";
    f("priority").value = t.priority;
    if (f("due")) f("due").value = t.due || "";
    if (f("sticky")) f("sticky").checked = !!t.sticky;
    currentId = t.id;
  } else {
    byId("task-form").reset();
    byId("priority").value = "medium";
    const main = qs("main.content");
    if (main?.dataset.view === "today" && byId("due")) byId("due").value = todayISO();
    if (main?.dataset.view === "calendar" && byId("due")) byId("due").value = byId("calDate")?.value || todayISO();
    currentId = null;
  }
  const dr = byId("drawer"); if (dr) dr.style.display = "block";
}
function closeDrawer(){ const dr = byId("drawer"); if (dr) dr.style.display = ""; }

function submitForm(e){
  e.preventDefault();
  const title = byId("title").value.trim();
  if (!title){ byId("title").reportValidity(); return; }

  let due = byId("due") ? byId("due").value : "";
  if (!due){
    const main = qs("main.content");
    if (main?.dataset.view === "today")    due = todayISO();
    if (main?.dataset.view === "calendar") due = byId("calDate")?.value || todayISO();
  }

  // Build task payload from form fields
  const data = {
    title,
    desc: (byId("desc").value || "").trim(),
    priority: /** @type {'high'|'medium'|'low'} */(byId("priority").value),
    due: due || undefined,
    dueTime: (byId("dueTime") && byId("dueTime").value) || undefined,
    sticky: !!(byId("sticky") && byId("sticky").checked)
  };

  // Create or update then persist
  if (currentId){
    const i = tasks.findIndex(t=>t.id===currentId);
    if (i>-1) tasks[i] = { ...tasks[i], ...data };
  } else {
    tasks.push({ id: uid(), done:false, createdAt:Date.now(), ...data });
  }
  save(); closeDrawer(); boot();
}

function deleteCurrent(){
  if (!currentId) return;
  if (confirm("Delete this task?")){
    tasks = tasks.filter(t=>t.id!==currentId);
    save(); closeDrawer(); boot();
  }
}

/* ---------- delegated click handling (rows, bars, notes) ---------- */
function delegatedClicks(e){
  const card = e.target.closest(".item, .bar, .note");
  if (!card) return;

  const id = card.dataset.id;
  const t = tasks.find(x=>x.id===id);
  if (!t) return;

  if (e.target.closest(".checkbox")){
    t.done = e.target.closest(".checkbox").checked;
    save(); boot();
  } else if (e.target.closest("button.edit") || card.classList.contains("bar") || card.classList.contains("note")){
    openDrawer(t);
  } else if (e.target.closest("button.del")){
    if (confirm("Delete this task?")){
      tasks = tasks.filter(x=>x.id!==id);
      save(); boot();
    }
  }
}

/* ---------- boot: refresh counts + header + view ---------- */
function boot(){
  counts();
  fmtHeader();
  updateGreeting();
  const view = qs("main.content")?.dataset.view;
  if (view === "today")    renderToday();
  if (view === "upcoming") renderUpcoming();
  if (view === "calendar") renderCalendar();
  if (view === "sticky")   renderSticky();
}

/* ---------- document-level events ---------- */
document.addEventListener("click", (e)=>{
  if (e.target.id === "toggleForm"){ openDrawer(null); }
  if (e.target.id === "cancelBtn"){
    const form = byId("task-form");
    if (form) {
      form.reset();
      const pr = byId("priority"); if (pr) pr.value = "medium";
      const st = byId("sticky");   if (st) st.checked = false;
    }
    currentId = null;
    closeDrawer();
  }
  if (e.target.id === "deleteBtn"){ deleteCurrent(); }
});
document.addEventListener("click", delegatedClicks);

/* ---------- form + search ---------- */
const form = byId("task-form"); if (form) form.addEventListener("submit", submitForm);
const search = byId("search"); if (search) search.addEventListener("input", ()=>{ searchText = search.value; boot(); });

/* ---------- calendar date change ---------- */
const calDate = byId("calDate"); if (calDate) calDate.addEventListener("change", renderCalendar);

/* ---------- sidebar visibility + floating FAB ---------- */
function setSidebarHidden(hidden){
  document.body.classList.toggle("sidebar-hidden", hidden);
  const sidebar = byId("sidebar");
  const menuBtn = byId("menuToggle");
  if (sidebar) sidebar.setAttribute("aria-hidden", hidden ? "true" : "false");
  if (menuBtn)  menuBtn.setAttribute("aria-expanded", hidden ? "false" : "true");
  localStorage.setItem("ui.sidebarHidden", hidden ? "1" : "0");
  updateFabVisibility();
}
function toggleSidebar(){
  setSidebarHidden(!document.body.classList.contains("sidebar-hidden"));
}
const menuBtn = byId("menuToggle");
if (menuBtn){ menuBtn.addEventListener("click", toggleSidebar); }

function ensureSidebarFab(){
  let fab = byId("menuToggleFab");
  if (!fab){
    fab = document.createElement("button");
    fab.id = "menuToggleFab";
    fab.className = "sidebar-fab iconbtn";
    fab.setAttribute("aria-label","Open menu");
    fab.textContent = "☰";
    document.body.appendChild(fab);
    fab.addEventListener("click", toggleSidebar);
  }
}
function updateFabVisibility(){
  const fab = byId("menuToggleFab");
  if (fab){
    fab.style.display = document.body.classList.contains("sidebar-hidden") ? "inline-flex" : "none";
  }
}

/* ---------- init: fab, sidebar state, nav highlight, first render ---------- */
ensureSidebarFab();
const initialHidden = localStorage.getItem("ui.sidebarHidden") === "1";
setSidebarHidden(initialHidden);
setActiveNav();
boot();
