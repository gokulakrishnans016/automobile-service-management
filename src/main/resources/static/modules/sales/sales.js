const SALES_API = API + "/sales";
const VEHICLE_API = API + "/vehicles";
const CUSTOMER_API = API + "/customers";

let vehicles = [];
let customers = [];
let salesData = [];

// ================= INIT =================
document.addEventListener("DOMContentLoaded", () => {
    loadVehicles();
    loadCustomers();
    loadSales();

    const search = document.getElementById("searchInput");

    if (search) {
        search.addEventListener("input", function () {
            const value = this.value.toLowerCase();

            const filtered = salesData.filter(s =>
                (s.customerName || "").toLowerCase().includes(value) ||
                String(s.id).includes(value)
            );

            renderSales(filtered);
        });
    }
});

// ================= LOAD VEHICLES =================
async function loadVehicles() {
    try {
        const res = await fetch(VEHICLE_API);
        const data = await res.json();

        vehicles = data.filter(v => v.status === "AVAILABLE");

        const dropdown = document.getElementById("vehicleId");
        dropdown.innerHTML = `<option value="">Select Vehicle</option>`;

        vehicles.forEach(v => {
            dropdown.innerHTML += `
                <option value="${v.id}">
                    ${v.vehicleName} (${v.brand}) - ₹${v.price}
                </option>
            `;
        });

    } catch (err) {
        console.error("Vehicle load error:", err);
    }
}

// ================= LOAD CUSTOMERS =================
async function loadCustomers() {
    try {
        const res = await fetch(CUSTOMER_API);
        const data = await res.json();

        customers = data;

        const dropdown = document.getElementById("customerId");
        dropdown.innerHTML = `<option value="">Select Customer</option>`;

        customers.forEach(c => {
            dropdown.innerHTML += `
                <option value="${c.id}">
                    ${c.name} (${c.phone})
                </option>
            `;
        });

    } catch (err) {
        console.error("Customer load error:", err);
    }
}

// ================= AUTO FILL PRICE =================
function autoFillPrice() {
    const id = document.getElementById("vehicleId").value;
    const v = vehicles.find(x => x.id == id);

    if (v) {
        document.getElementById("salePrice").value = v.price;
    }
}

// ================= LOAD SALES =================
async function loadSales() {
    try {
        const res = await fetch(SALES_API);
        const data = await res.json();

        salesData = data;

        renderSales(data);
    } catch (err) {
        console.error("Sales load error:", err);
    }
}

// ================= RENDER SALES =================
function renderSales(data) {
    const table = document.getElementById("salesTable");
    table.innerHTML = "";

    data.forEach(s => {
        table.innerHTML += `
            <tr>
                <td>${s.vehicleName}</td>
                <td>${s.customerName}</td>
                <td>₹${s.salePrice}</td>

                <td>
                    <span class="status ${s.status.toLowerCase()}">
                        ${s.status}
                    </span>
                </td>

                <td>${s.deliveryDate || "-"}</td>

                <td class="action-buttons">
                    <button onclick="deleteSale(${s.id})" class="btn-delete">
                        Delete
                    </button>

                    <button onclick="cancelSale(${s.id})" class="btn-cancel">
                        Cancel
                    </button>
                </td>
            </tr>
        `;
    });
}

// ================= ADD SALE =================
async function addSale() {

    const data = {
        vehicleId: parseInt(document.getElementById("vehicleId").value),
        customerId: parseInt(document.getElementById("customerId").value),
        salePrice: parseFloat(document.getElementById("salePrice").value),
        discount: parseFloat(document.getElementById("discount").value) || 0,
        taxAmount: parseFloat(document.getElementById("taxAmount").value) || 0,
        status: document.getElementById("status").value,
        deliveryDate: document.getElementById("deliveryDate").value
    };

    if (!data.vehicleId || !data.customerId || !data.salePrice) {
        alert("Fill required fields");
        return;
    }

    try {
        const res = await fetch(SALES_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            const err = await res.text();
            alert(err);
            return;
        }

        alert("Sale Added ✅");

        closeModal();
        loadSales();
        loadVehicles();

    } catch (err) {
        console.error(err);
        alert("Error adding sale");
    }
}

// ================= DELETE =================
async function deleteSale(id) {
    if (!confirm("Delete this sale?")) return;

    try {
        await fetch(`${SALES_API}/${id}`, { method: "DELETE" });

        loadSales();
        loadVehicles();

    } catch (err) {
        console.error(err);
    }
}

// ================= CANCEL SALE =================
async function cancelSale(id) {

    if (!confirm("Cancel this sale?")) return;

    try {
        await fetch(`${API}/sales/cancel/${id}`, { method: "PUT" });

        alert("Sale Cancelled ❌");

        loadSales();
        loadVehicles();

    } catch (err) {
        console.error(err);
        alert("Error cancelling sale");
    }
}

// ================= MODAL =================
function openModal() {
    document.getElementById("saleModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("saleModal").classList.add("hidden");
}

// ================= LOGOUT =================
function logout() {
    localStorage.removeItem("user");
    window.location.href = "/app/login.html";
}