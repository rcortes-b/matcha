async function loadComponent(id, file) {
  const res = await fetch(file);
  const data = await res.text();

  const element = document.getElementById(id);
  if (element) element.innerHTML = data;
}

function loadDropdown() {
	const buttonProfile = document.getElementById('dropdown-button');
	const dropdownMenu = document.getElementById('dropdown');

	if (!buttonProfile || !dropdownMenu)
		return;
	
	buttonProfile.addEventListener('click', () => {
		//dropdownMenu.classList.toggle('show');
		if (dropdownMenu.style.display == 'flex')
			dropdownMenu.style.display = 'none';
		else
			dropdownMenu.style.display = 'flex';
	});
}

async function loadComponents() {
	await loadComponent("navbar", "components/navbar.html");
	await loadComponent("footer", "components/footer.html");

	const buttonProfile = document.getElementById('dropdown-button');
	const dropdownMenu = document.getElementById('dropdown');

	loadDropdown();
	logout();
}

function logout() {
    const logoutButton = document.getElementById('logout-button');
    if (!logoutButton) return; // element not ready yet

    logoutButton.addEventListener('click', async (e) => {
        e.preventDefault();
        localStorage.removeItem('user-connected');
        console.log('Local storage cleared');

        try {
            const res = await fetch("http://localhost:9090/api/auth/logout", {
                method: "POST",
                credentials: "include",
            });
            console.log(res.ok ? 'Logged out!' : 'Logout failed');
        } catch (err) {
            console.error(err);
        }
		window.location.href = '/';
        // Optionally update UI
        // e.g., show login link, hide profile link
    });
}

window.componentsLoaded = loadComponents();