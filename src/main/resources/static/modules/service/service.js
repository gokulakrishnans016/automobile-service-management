const SERVICE_API = API + "/vehicle-services";
const VEHICLE_API = API + "/vehicles";

let vehicles = [];

// GET ROLE
const user = JSON.parse(localStorage.getItem("user"));
const role = user?.role;

// ================= LOAD =================
document.addEventListener("DOMContentLoaded", () => {

    loadVehicles();
    loadServices();

    document.getElementById("serviceModal").classList.add("hidden");

    if (role === "USER") {
        document.querySelector(".add-btn").style.display = "none";
        document.getElementById("actionHeader").style.display = "none";
    }
});

// ================= LOAD VEHICLES =================
async function loadVehicles() {
    const res = await fetch(VEHICLE_API);
    vehicles = await res.json();

    const select = document.getElementById("vehicle");
    select.innerHTML = `<option value="">Select Vehicle</option>`;

    vehicles.forEach(v => {

        if (!v.customerId) return;

        select.innerHTML += `
            <option value="${v.id}">
                ${v.vehicleName} - ${v.customerName}
            </option>
        `;
    });
}

// ================= AUTO FILL =================
function autoFillCustomer(vehicleId) {

    const vehicle = vehicles.find(v => v.id == vehicleId);
    if (!vehicle) return;

    document.getElementById("autoVehicle").innerText = vehicle.vehicleName || "-";

    if (vehicle.customerId) {
        document.getElementById("autoCustomer").innerText = vehicle.customerName;
        document.getElementById("customerId").value = vehicle.customerId;
    } else {
        document.getElementById("autoCustomer").innerText = "Not Assigned";
        document.getElementById("customerId").value = "";
    }
}

// ================= SAVE =================
async function saveService(e) {
    e.preventDefault();

    if (role === "USER") {
        alert("❌ You are not allowed to add service");
        return;
    }

    const data = {
        vehicleId: parseInt(document.getElementById("vehicle").value),
        customerId: parseInt(document.getElementById("customerId").value),
        serviceType: getSelectedServices(),
        serviceDescription: document.getElementById("desc").value,
        serviceCost: parseFloat(document.getElementById("cost").value),
        nextServiceDate: document.getElementById("nextDate").value
    };

    const res = await fetch(`${API}/vehicle-services?username=${user.username}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    if (!res.ok) {
        const err = await res.text();
        alert("❌ Error: " + err);
        return;
    }

    alert("✅ Saved");
    closeModal();
    loadServices();
}

// ================= LOAD SERVICES =================
async function loadServices() {

    const res = await fetch(`${SERVICE_API}?username=${user.username}`);
    const data = await res.json();

    const table = document.getElementById("serviceTable");
    table.innerHTML = "";

    data.forEach(s => {

        table.innerHTML += `
            <tr>
                <td>${s.vehicleName}</td>
                <td>${s.customerName}</td>
                <td>${s.serviceType.replaceAll(",", ", ")}</td>
                <td>₹${s.serviceCost}</td>
                <td>${s.status}</td>
                <td>${s.nextServiceDate}</td>

                ${role === "ADMIN" ? `
                <td>
                    <div class="action-buttons">

                        <button class="btn-complete"
                            onclick="updateStatus(${s.id})"
                            ${s.status === "COMPLETED" ? "disabled" : ""}>
                            <i class="fa-solid fa-check"></i>
                        </button>

                        <button class="btn-delete"
                            onclick="deleteService(${s.id})">
                            <i class="fa-solid fa-trash"></i>
                        </button>

                    </div>
                </td>
                ` : ""}
            </tr>
        `;
    });
}

// ================= UPDATE =================
async function updateStatus(id) {

    if (role === "USER") return;

    await fetch(`${SERVICE_API}/${id}/status?username=${user.username}&status=COMPLETED`, {
        method: "PUT"
    });

    loadServices();
}

// ================= DELETE =================
async function deleteService(id) {

    if (role === "USER") return;

    if (!confirm("Delete?")) return;

    await fetch(`${SERVICE_API}/${id}?username=${user.username}`, {
        method: "DELETE"
    });

    loadServices();
}

// ================= MODAL =================
function openModal() {

    if (role === "USER") return;

    document.getElementById("serviceModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("serviceModal").classList.add("hidden");
}

// ================= CHECKBOX =================
function getSelectedServices() {
    const checkboxes = document.querySelectorAll(".checkbox-group input:checked");
    return Array.from(checkboxes).map(cb => cb.value);
}