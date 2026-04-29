// GLOBAL API
const CUSTOMER_API = API + "/customers";

let editingId = null;

// ================= LOAD =================
document.addEventListener("DOMContentLoaded", loadCustomers);

async function loadCustomers() {
    try {
        const res = await fetch(CUSTOMER_API);
        const data = await res.json();

        const table = document.getElementById("customerTable");
        table.innerHTML = "";

        data.forEach(c => {
            table.innerHTML += `
                <tr>
                    <td>${c.name}</td>
                    <td>${c.phone}</td>
                    <td>${c.city}</td>
                    <td>${c.drivingLicenseNumber}</td>
                    <td>
                        <button onclick='editCustomer(${JSON.stringify(c)})'>Edit</button>
                        <button onclick="deleteCustomer(${c.id})">Delete</button>
                    </td>
                </tr>
            `;
        });

    } catch (err) {
        console.error("Load error:", err);
    }
}

// ================= MODAL =================

function openModal() {
    clearForm();

    editingId = null;

    document.getElementById("modalTitle").innerText = "Add Customer";
    document.getElementById("customerModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("customerModal").classList.add("hidden");
}

// ================= SAVE =================

function getVal(id) {
    const el = document.getElementById(id);
    return el ? el.value : null;
}

async function saveCustomer() {

    const data = {
        name: getVal("name"),
        age: parseInt(getVal("age")) || null,
        dateOfBirth: getVal("dateOfBirth"),
        gender: getVal("gender"),

        email: getVal("email"),
        phone: getVal("phone"),

        address: getVal("address"),
        city: getVal("city"),
        state: getVal("state"),
        pincode: getVal("pincode"),

        drivingLicenseNumber: getVal("license")
    };

    console.log("DATA:", data);

    let url = CUSTOMER_API;
    let method = "POST";

    if (editingId) {
        url = `${CUSTOMER_API}/${editingId}`;
        method = "PUT";
    }

    try {
        const res = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            const err = await res.text();
            throw new Error(err);
        }

        alert(editingId ? "Updated ✅" : "Saved ✅");

        editingId = null;
        closeModal();
        loadCustomers();

    } catch (err) {
        alert("Error: " + err.message);
        console.error(err);
    }
}

// ================= EDIT =================

function editCustomer(c) {

    editingId = c.id;
    document.getElementById("modalTitle").innerText = "Edit Customer";
    document.getElementById("customerModal").classList.remove("hidden");

    document.getElementById("name").value = c.name || "";
    document.getElementById("age").value = c.age || "";
    document.getElementById("dateOfBirth").value = c.dateOfBirth || "";
    document.getElementById("gender").value = c.gender || "";
    document.getElementById("email").value = c.email || "";
    document.getElementById("phone").value = c.phone || "";
    document.getElementById("address").value = c.address || "";
    document.getElementById("city").value = c.city || "";
    document.getElementById("state").value = c.state || "";
    document.getElementById("pincode").value = c.pincode || "";
    document.getElementById("license").value = c.drivingLicenseNumber || "";
}

// ================= DELETE =================

async function deleteCustomer(id) {
    if (!confirm("Delete this customer?")) return;

    try {
        await fetch(`${CUSTOMER_API}/${id}`, {
            method: "DELETE"
        });

        loadCustomers();

    } catch (err) {
        console.error("Delete error:", err);
    }
}

// ================= CLEAR FORM =================

function clearForm() {
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    document.getElementById("dateOfBirth").value = "";
    document.getElementById("gender").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("address").value = "";
    document.getElementById("city").value = "";
    document.getElementById("state").value = "";
    document.getElementById("pincode").value = "";
    document.getElementById("license").value = "";
}

// 🔍 SEARCH FILTER
document.addEventListener("DOMContentLoaded", () => {
    loadCustomers();

    const searchInput = document.getElementById("searchInput");

    if (searchInput) {
        searchInput.addEventListener("keyup", () => {
            const value = searchInput.value.toLowerCase();
            const rows = document.querySelectorAll("#customerTable tr");

            rows.forEach(row => {
                row.style.display = row.innerText.toLowerCase().includes(value)
                    ? ""
                    : "none";
            });
        });
    }
});

// ================= LOGOUT =================

function logout() {
    localStorage.removeItem("user");
    window.location.href = "/app/login.html";
}