const API = "http://localhost:8080";

function getUser() {
    return JSON.parse(localStorage.getItem("user"));
    }

function isAdmin() {
    return getUser()?.role === "ADMIN";
}

function go(page) {
    const routes = {
        dashboard: "/app/dashboard.html",
        vehicle: "/modules/vehicle/vehicle.html",
        customer: "/modules/customer/customer.html",
        sales: "/modules/sales/sales.html",
        payment: "/modules/payment/payment.html",
        service: "/modules/service/service.html"
    };

    window.location.href = routes[page];
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "/app/login.html";
}