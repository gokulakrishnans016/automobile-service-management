document.addEventListener("DOMContentLoaded", () => {

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
        window.location.href = "/app/login.html";
        return;
    }

    loadCounts(user);
});

/* ================= LOAD COUNTS ================= */
function loadCounts(user) {

    fetch(`${API}/vehicles`)
        .then(res => res.json())
        .then(data => animateValue("vehicleCount", data.length))
        .catch(() => setZero("vehicleCount"));

    fetch(`${API}/customers`)
        .then(res => res.json())
        .then(data => animateValue("customerCount", data.length))
        .catch(() => setZero("customerCount"));

    fetch(`${API}/sales`)
        .then(res => res.json())
        .then(data => animateValue("salesCount", data.length))
        .catch(() => setZero("salesCount"));

    fetch(`${API}/payments?username=${user.username}`)
        .then(res => res.json())
        .then(data => animateValue("paymentCount", data.length))
        .catch(() => setZero("paymentCount"));

    fetch(`${API}/vehicle-services/count?username=${user.username}`)
        .then(res => res.json())
        .then(data => animateValue("serviceCount", data))
        .catch(() => setZero("serviceCount"));
}

/* ================= ANIMATION ================= */
function animateValue(id, end, duration = 800) {
    const obj = document.getElementById(id);
    if (!obj) return;

    if (end === 0) {
        obj.innerText = 0;
        return;
    }

    let startTime = null;

    function animation(currentTime) {
        if (!startTime) startTime = currentTime;

        const progress = Math.min((currentTime - startTime) / duration, 1);
        obj.innerText = Math.floor(progress * end);

        if (progress < 1) {
            requestAnimationFrame(animation);
        }
    }

    requestAnimationFrame(animation);
}

/* ================= SAFE ZERO ================= */
function setZero(id) {
    const el = document.getElementById(id);
    if (el) el.innerText = 0;
}