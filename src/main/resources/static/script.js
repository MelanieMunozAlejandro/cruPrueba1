const loginBtn = document.getElementById('loginBtn');
const logoutBtn = document.getElementById('logoutBtn');
const usernameDisplay = document.getElementById('usernameDisplay');
const userDetails = document.getElementById('userDetails');

// Manejar el inicio de sesión
loginBtn.addEventListener('click', async () => {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${username}&password=${password}`,
    });

    if (response.ok) {
        const userData = await response.text();
        userDetails.style.display = 'block';
        usernameDisplay.textContent = `Usuario: ${username}`;
    } else {
        alert('Inicio de sesión fallido');
    }
});

// Manejar el cierre de sesión
logoutBtn.addEventListener('click', async () => {
    const response = await fetch('/api/auth/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `userId=1`, // Cambia esto con el ID del usuario actual
    });

    if (response.ok) {
        userDetails.style.display = 'none';
        usernameDisplay.textContent = '';
    }
});
const registerPageBtn = document.getElementById('registerPageBtn');

registerPageBtn.addEventListener('click', () => {
    window.location.href = 'register.html'; // Cambia esto a la ruta de tu página de registro
});

const registerBtn = document.getElementById('registerBtn');

registerBtn.addEventListener('click', async () => {
    const registerUsername = document.getElementById('registerUsername').value;
    const registerPassword = document.getElementById('registerPassword').value;
    const registerEmail = document.getElementById('registerEmail').value;

    const response = await fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userName: registerUsername,
            password: registerPassword,
            mail: registerEmail,
            // ... Otros campos necesarios para el registro ...
        }),
    });

    if (response.ok) {
        window.location.href = 'login.html'; // Cambia esto a la ruta de tu página de inicio de sesión
    } else {
        alert('Error en el registro');
    }
});




