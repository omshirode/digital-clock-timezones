const allTimezones = [
    { name: 'America/New_York', offset: -5 },
    { name: 'America/Chicago', offset: -6 },
    { name: 'America/Denver', offset: -7 },
    { name: 'America/Los_Angeles', offset: -8 },
    { name: 'Europe/London', offset: 0 },
    { name: 'Europe/Paris', offset: 1 },
    { name: 'Europe/Berlin', offset: 1 },
    { name: 'Europe/Moscow', offset: 3 },
    { name: 'Asia/Dubai', offset: 4 },
    { name: 'Asia/Kolkata', offset: 5.5 },
    { name: 'Asia/Bangkok', offset: 7 },
    { name: 'Asia/Singapore', offset: 8 },
    { name: 'Asia/Hong_Kong', offset: 8 },
    { name: 'Asia/Shanghai', offset: 8 },
    { name: 'Asia/Tokyo', offset: 9 },
    { name: 'Asia/Seoul', offset: 9 },
    { name: 'Australia/Sydney', offset: 10 },
    { name: 'Pacific/Auckland', offset: 12 },
    { name: 'Africa/Cairo', offset: 2 },
    { name: 'Africa/Johannesburg', offset: 2 },
];

const majorCities = [
    { name: 'America/New_York', offset: -5 },
    { name: 'America/Los_Angeles', offset: -8 },
    { name: 'Europe/London', offset: 0 },
    { name: 'Europe/Paris', offset: 1 },
    { name: 'Asia/Tokyo', offset: 9 },
    { name: 'Australia/Sydney', offset: 10 },
];

function showView(viewId) {
    document.querySelectorAll('.view').forEach(v => v.classList.remove('active'));
    document.getElementById(viewId).classList.add('active');
}

function updateClocks() {
    const now = new Date();
    const utcH = now.getUTCHours();
    const min = String(now.getUTCMinutes()).padStart(2, '0');
    const sec = String(now.getUTCSeconds()).padStart(2, '0');

    updateDisplay('clock-main', utcH, min, sec, -5);

    majorCities.forEach((tz, i) => {
        updateDisplay(`world-clock-${i}`, utcH, min, sec, tz.offset);
    });
}

function updateDisplay(id, utcH, min, sec, offset) {
    const el = document.getElementById(id);
    if (!el) return;
    
    let h = utcH + Math.floor(offset);
    let m = parseInt(min) + Math.floor((offset % 1) * 60);
    
    if (m >= 60) { m -= 60; h += 1; }
    if (h < 0) h += 24;
    if (h >= 24) h -= 24;
    
    el.textContent = `${String(h).padStart(2, '0')}:${String(m).padStart(2, '0')}:${sec}`;
}

function filterTimezones() {
    const query = document.getElementById('searchInput').value.toLowerCase();
    const list = document.getElementById('dropdownList');
    const stats = document.getElementById('searchStats');
    list.innerHTML = '';

    const filtered = allTimezones.filter(tz => 
        tz.name.toLowerCase().includes(query)
    );
    const results = filtered.slice(0, 15);
    
    results.forEach(tz => {
        const div = document.createElement('div');
        div.className = 'dropdown-item';
        div.innerHTML = `<span class="tz-name">${tz.name}</span><span class="tz-offset">UTC${tz.offset >= 0 ? '+' : ''}${tz.offset}</span>`;
        div.onclick = () => {
            showView('view-clock');
            document.querySelector('#view-clock .timezone-name').textContent = tz.name;
        };
        list.appendChild(div);
    });

    stats.textContent = `Showing ${results.length} of ${filtered.length} results`;
}

function populateWorldClocks() {
    const grid = document.getElementById('worldClocksGrid');
    grid.innerHTML = '';
    majorCities.forEach((tz, i) => {
        const card = document.createElement('div');
        card.className = 'clock-card';
        card.innerHTML = `
            <div class="card-header">
                <h3>${tz.name}</h3>
            </div>
            <div class="card-time" id="world-clock-${i}">12:00:00</div>
            <div class="card-date">Current Time</div>
            <div class="card-offset">UTC${tz.offset >= 0 ? '+' : ''}${tz.offset}</div>
        `;
        grid.appendChild(card);
    });
}

function toggleFavorite() {
    event.target.classList.toggle('active');
}

document.addEventListener('DOMContentLoaded', () => {
    filterTimezones();
    updateClocks();
    populateWorldClocks();
    setInterval(updateClocks, 1000);
});