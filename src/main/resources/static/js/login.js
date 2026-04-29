const API = "http://localhost:8080";

async function login(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const res = await fetch(`${API}/api/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });

        if (!res.ok) {
            alert("Invalid credentials ");
            return;
        }

        const data = await res.json();

        console.log("Login response:", data);

        //  STORE USER
        localStorage.setItem("user", JSON.stringify({
            username: data.username,
            role: data.role
        }));

        //  REDIRECT
        window.location.href = "/app/dashboard.html";

    } catch (err) {
        console.error(err);
        alert("Server error ");
    }
}