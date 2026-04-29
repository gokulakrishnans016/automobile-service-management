document.getElementById("registerForm").addEventListener("submit", async function(e) {
    e.preventDefault();

    const userData = {
        name: document.getElementById("name").value,
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        dateOfBirth: document.getElementById("dob").value,
        gender: document.getElementById("gender").value,
        phoneNumber: document.getElementById("phone").value,
        password: document.getElementById("password").value
    };

    try {
        const res = await fetch("http://localhost:8080/users/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userData)
        });

        if (res.ok) {
            alert("✅ Registered Successfully!");
            window.location.href = "login.html";
        } else {
            alert("❌ Registration failed");
        }

    } catch (err) {
        console.error(err);
        alert("Server error");
    }
});
