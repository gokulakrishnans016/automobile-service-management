const API_URL = API + "/vehicles";

let editMode = false;

const ITEMS_PER_PAGE = 5;
let currentAvailablePage = 1;
let currentSoldPage = 1;

/* LOAD */
document.addEventListener("DOMContentLoaded", () => {
    loadVehicles();
    showSection("available");
});

/* LOAD TABLE WITH PAGINATION */
async function loadVehicles() {
    try {
        const res = await fetch(API_URL);
        const data = await res.json();

        const inventoryTable = document.getElementById("vehicleTable");
        const soldTable = document.getElementById("soldTable");

        inventoryTable.innerHTML = "";
        soldTable.innerHTML = "";

        const availableVehicles = data.filter(v => v.status === "AVAILABLE");
        const soldVehicles = data.filter(v => v.status === "SOLD");

        renderTable(availableVehicles, currentAvailablePage, "vehicleTable", true);
        renderTable(soldVehicles, currentSoldPage, "soldTable", false);

        setupPagination(availableVehicles, "availablePagination", true);
        setupPagination(soldVehicles, "soldPagination", false);

    } catch (err) {
        console.error("Load error:", err);
    }
}

function showSection(type) {

    const available = document.getElementById("availableSection");
    const sold = document.getElementById("soldSection");

    const btnA = document.getElementById("btnAvailable");
    const btnS = document.getElementById("btnSold");

    if (type === "available") {
        available.style.display = "block";
        sold.style.display = "none";

        btnA.classList.add("active");
        btnS.classList.remove("active");
    } else {
        available.style.display = "none";
        sold.style.display = "block";

        btnS.classList.add("active");
        btnA.classList.remove("active");
    }
}

function renderTable(data, page, tableId, isAvailable) {

    const table = document.getElementById(tableId);
    table.innerHTML = "";

    const start = (page - 1) * ITEMS_PER_PAGE;
    const end = start + ITEMS_PER_PAGE;

    const items = data.slice(start, end);

    if (items.length === 0) {
        table.innerHTML = `
            <tr>
                <td colspan="${isAvailable ? 9 : 8}" style="text-align:center;color:#94a3b8;">
                    Not Available
                </td>
            </tr>
        `;
        return;
    }

    items.forEach(v => {

        if (isAvailable) {
            table.innerHTML += `
                <tr>
                    <td>${v.vehicleName}</td>
                    <td>${v.brand}</td>
                    <td>${v.model}</td>
                    <td>${v.price}</td>
                    <td>${v.stockQuantity}</td>
                    <td>${v.fuelType}</td>
                    <td>${v.manufactureYear}</td>
                    <td>${v.status}</td>
                    <td>
                        <button onclick="editVehicle(${v.id})">Edit</button>
                        <button onclick="deleteVehicle(${v.id})">Delete</button>
                    </td>
                </tr>
            `;
        } else {
            table.innerHTML += `
                <tr>
                    <td>${v.vehicleName}</td>
                    <td>${v.brand}</td>
                    <td>${v.model}</td>
                    <td>${v.customerName || "Not Available"}</td>
                    <td>${v.price}</td>
                    <td>${v.fuelType}</td>
                    <td>${v.manufactureYear}</td>
                    <td style="color:#22c55e;">${v.status}</td>
                </tr>
            `;
        }

    });
}

function setupPagination(data, containerId, isAvailable) {

    const container = document.getElementById(containerId);
    container.innerHTML = "";

    const totalPages = Math.ceil(data.length / ITEMS_PER_PAGE);

    if (totalPages <= 1) return;

    for (let i = 1; i <= totalPages; i++) {

        const btn = document.createElement("button");
        btn.innerText = i;

        btn.style.margin = "5px";
        btn.style.padding = "6px 12px";
        btn.style.borderRadius = "6px";
        btn.style.border = "none";
        btn.style.background = "#1e293b";
        btn.style.color = "white";
        btn.style.cursor = "pointer";

        btn.onclick = () => {
            if (isAvailable) {
                currentAvailablePage = i;
            } else {
                currentSoldPage = i;
            }
            loadVehicles();
        };

        container.appendChild(btn);
    }
}

/* MODAL */
function openModal() {
    editMode = false;
    clearForm();

    document.getElementById("modalTitle").innerText = "Add Vehicle";
    document.getElementById("saveBtn").innerText = "Save Vehicle";

    document.getElementById("vehicleModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("vehicleModal").classList.add("hidden");
}

//ADD VEHICLE
async function addVehicle() {

    const vehicle = {
        vehicleName: document.getElementById("name").value,
        brand: document.getElementById("brand").value,
        model: document.getElementById("model").value,
        price: parseFloat(document.getElementById("price").value),
        stockQuantity: parseInt(document.getElementById("stock").value),
        fuelType: document.getElementById("fuel").value,
        manufactureYear: parseInt(document.getElementById("year").value),
        registrationNumber: document.getElementById("reg").value,
        status: document.getElementById("status").value
    };

    let method = "POST";
    let url = API + "/vehicles";

    const id = document.getElementById("vehicleId").value;

    if (id) {
        method = "PUT";
        url = `${API}/vehicles/${id}`;
    }

    await fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(vehicle)
    });

    closeModal();
    loadVehicles();
}

// EDIT
async function editVehicle(id) {
    try {
        const res = await fetch(`${API_URL}/${id}`);
        const v = await res.json();

        editMode = true;

        document.getElementById("vehicleId").value = v.id;
        document.getElementById("name").value = v.vehicleName;
        document.getElementById("brand").value = v.brand;
        document.getElementById("model").value = v.model;
        document.getElementById("price").value = v.price;
        document.getElementById("stock").value = v.stockQuantity;
        document.getElementById("fuel").value = v.fuelType;
        document.getElementById("year").value = v.manufactureYear;
        document.getElementById("reg").value = v.registrationNumber;
        document.getElementById("status").value = v.status;

        document.getElementById("modalTitle").innerText = "Edit Vehicle";
        document.getElementById("saveBtn").innerText = "Update Vehicle";

        document.getElementById("vehicleModal").classList.remove("hidden");

    } catch (err) {
        console.error("Edit error:", err);
    }
}

// DELETE
async function deleteVehicle(id) {
    if (!confirm("Delete this vehicle?")) return;

    try {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        loadVehicles();
    } catch (err) {
        console.error("Delete error:", err);
    }
}

// CLEAR FORM
function clearForm() {
    document.getElementById("vehicleId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("brand").value = "";
    document.getElementById("model").value = "";
    document.getElementById("price").value = "";
    document.getElementById("stock").value = "";
    document.getElementById("fuel").value = "";
    document.getElementById("year").value = "";
    document.getElementById("reg").value = "";
    document.getElementById("status").value = "";
}

// SEARCH
document.getElementById("searchInput").addEventListener("input", function () {

    const value = this.value.toLowerCase();

    const availableRows = document.querySelectorAll("#vehicleTable tr");
    const soldRows = document.querySelectorAll("#soldTable tr");

    let availableVisible = 0;
    let soldVisible = 0;

    availableRows.forEach(row => {
        if (row.innerText.toLowerCase().includes(value)) {
            row.style.display = "";
            availableVisible++;
        } else {
            row.style.display = "none";
        }
    });

    soldRows.forEach(row => {
        if (row.innerText.toLowerCase().includes(value)) {
            row.style.display = "";
            soldVisible++;
        } else {
            row.style.display = "none";
        }
    });

    document.getElementById("availableSection").style.display =
        availableVisible > 0 ? "block" : "none";

    document.getElementById("soldSection").style.display =
        soldVisible > 0 ? "block" : "none";
});

// LOGOUT
function logout() {
    localStorage.removeItem("user");
    window.location.href = "/app/login.html";
}