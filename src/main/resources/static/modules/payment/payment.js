
let sales = [];
let summary = [];

const user = JSON.parse(localStorage.getItem("user"));

if (user.role === "USER") {
    document.getElementById("addBtn").style.display = "none";
}

document.addEventListener("DOMContentLoaded", () => {

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
        window.location.href = "/app/login.html";
        return;
    }

    document.getElementById("addBtn").addEventListener("click", openAdd);
    document.getElementById("closeBtn").addEventListener("click", closeModal);
    document.getElementById("saveBtn").addEventListener("click", save);

    document.getElementById("saleSelect").addEventListener("change", fillDetails);
    document.getElementById("searchBox").addEventListener("input", render);

    loadSummary();
});


// ================= LOAD SALES =================
function loadSales() {
    fetch(`${API}/sales`)
        .then(res => res.json())
        .then(data => {
            sales = data;

            const dropdown = document.getElementById("saleSelect");
            dropdown.innerHTML = `<option value="">Select Sale</option>`;

            data.forEach(s => {

                const payment = summary.find(p => p.saleId == s.id);

                //  show only pending payments
                if (!payment || payment.remainingAmount > 0) {
                    dropdown.innerHTML += `
                        <option value="${s.id}">
                            ${s.vehicleName} - ${s.customerName} (₹${s.finalAmount})
                        </option>
                    `;
                }

            });

            // OPTIONAL UX
            if (dropdown.options.length === 1) {
                dropdown.innerHTML = `<option>No pending payments</option>`;
            }
        });
}


// ================= LOAD SUMMARY =================
function loadSummary() {
    const user = JSON.parse(localStorage.getItem("user"));

    fetch(`${API}/payments/summary?username=${user.username}`)
        .then(res => res.json())
        .then(data => {
            summary = data;

            render();

            loadSales();
        });
}


// ================= RENDER =================
function render() {

    const search = document.getElementById("searchBox").value.toLowerCase();
    const container = document.getElementById("paymentContainer");

    container.innerHTML = "";

    summary
        .filter(s =>
            s.customerName.toLowerCase().includes(search) ||
            String(s.customerId).includes(search) ||
            String(s.saleId).includes(search)
        )
        .forEach(s => {

            container.innerHTML += `
                <div class="card">
                    <h3>${s.vehicleName} - ${s.customerName} (Sale ID: ${s.saleId})</h3>

                    <p>Total: ₹${s.totalAmount}</p>
                    <p class="green">Paid: ₹${s.paidAmount}</p>
                    <p class="red">Remaining: ₹${s.remainingAmount}</p>

                    <p>Status: <b>${s.remainingAmount === 0 ? 'SUCCESS' : 'PENDING'}</b></p>

                    ${s.remainingAmount > 0
                        ? `<button onclick="payRemaining(${s.saleId}, ${s.remainingAmount})">Pay Remaining</button>`
                        : ''
                    }
                </div>
            `;
        });
}


// ================= OPEN MODAL =================
function openAdd() {
    document.getElementById("modal").classList.remove("hidden");
}


// ================= CLOSE MODAL =================
function closeModal() {
    document.getElementById("modal").classList.add("hidden");
}


// ================= FILL DETAILS =================
function fillDetails() {

    const saleId = document.getElementById("saleSelect").value;
    const s = sales.find(x => x.id == saleId);

    if (!s) return;

    document.getElementById("detailsBox").innerHTML = `
        Customer: ${s.customerName}<br>
        Vehicle: ${s.vehicleName}<br>
        Total: ₹${s.finalAmount}
    `;

    document.getElementById("amount").value = s.finalAmount;
}


// ================= SAVE =================
function save() {

    const user = JSON.parse(localStorage.getItem("user"));

    const saleId = document.getElementById("saleSelect").value;
    const amount = parseFloat(document.getElementById("amount").value);

    const payment = summary.find(p => p.saleId == saleId);

    //  PREVENT OVERPAYMENT
    if (payment && amount > payment.remainingAmount) {
        alert(`You can only pay up to ₹${payment.remainingAmount}`);
        return;
    }

    const data = {
        saleId: saleId,
        amount: amount,
        paymentMethod: document.getElementById("method").value,
        transactionId: document.getElementById("txn").value
    };

    if (!data.saleId || !data.amount) {
        alert("Fill all fields");
        return;
    }

    fetch(`${API}/payments?username=${user.username}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error("Payment failed");
        return res.json();
    })
    .then(() => {
        alert("Payment Added ✅");
        closeModal();

        loadSummary();
    })
    .catch(err => alert(err.message));
}


// ================= PAY REMAINING =================
function payRemaining(saleId, remaining) {

    document.getElementById("modal").classList.remove("hidden");

    document.getElementById("saleSelect").value = saleId;
    document.getElementById("amount").value = remaining;

    fillDetails();
}