// AUTO CHECK LOGIN STATUS
document.addEventListener("DOMContentLoaded", () => {

    const token = localStorage.getItem("token");

    // If user already logged in
    if (token) {
        console.log("User already logged in");
    }

});


// NAVIGATION FUNCTIONS

// LOGIN BUTTON CLICK
function goToLogin() {
    const token = localStorage.getItem("token");

    if (token) {
        // already logged in → go dashboard
        window.location.href = "dashboard.html";
    } else {
        window.location.href = "login.html";
    }
}

// REGISTER BUTTON CLICK
function goToRegister() {
    window.location.href = "register.html";
}

// LOGOUT (optional for future)
function logout() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
}

// SMOOTH SCROLL
document.querySelectorAll("a[href^='#']").forEach(anchor => {
    anchor.addEventListener("click", function (e) {
        e.preventDefault();

        const target = document.querySelector(this.getAttribute("href"));

        if (target) {
            target.scrollIntoView({
                behavior: "smooth"
            });
        }
    });
});


document.addEventListener("DOMContentLoaded", () => {

    const loginBtn = document.querySelector(".login-btn");
    const registerBtn = document.querySelector(".register-btn");

    if (loginBtn) {
        loginBtn.addEventListener("click", goToLogin);
    }

    if (registerBtn) {
        registerBtn.addEventListener("click", goToRegister);
    }

});