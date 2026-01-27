
function displayNavBar(validRequest) {

	const navBar = document.getElementById('navBar');
	const loggedNavBar = document.getElementById('loggedNavBar');

	if (validRequest) {
		loggedNavBar.style.display = 'block';
		navBar.style.display = 'none';
	} else {
		loggedNavBar.style.display = 'none';
		navBar.style.display = 'block';
	}

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

getNearestPicks();

