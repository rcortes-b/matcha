await window.componentsLoaded;

async function displayNavBar() {
	const loader = document.getElementById('loader');
	// if localStorage user is logged just display y sino pues activar el loading
	let navlinks;
	if (true == true) {
		loader.style.display = 'flex';
		const res = await fetch("http://localhost:9090/api/auth/me", {
			method: "POST"
		});

		if (res.status == 200)
			navlinks = document.getElementById('navlinks-auth');
		else
			navlinks = document.getElementById('navlinks');
		if (navlinks)
			navlinks.style.display = 'flex';
	} else {
		navlinks = document.getElementById('navlinks-auth');
		if (navlinks)
			navlinks.style.display = 'flex';
	}
	loader.style.display = 'none';
}

async function getNearestPicks() {
	const res = await fetch("http://localhost:9090/api/browse&by=distance", {
		method: "POST"
	});
	displayNavBar(res.ok); 
	if (res.ok) {

	} else {

		console.log("Wrong input!");
	}
}


displayNavBar();
//getNearestPicks();

document.getElementById('browsing-button').onclick = () => window.location.href = "/browse";
