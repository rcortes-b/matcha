fetch("components/navbar.html")
  .then(res => res.text())
  .then(data => {
    const navbar = document.getElementById("navbar");
    if (navbar) {
      navbar.innerHTML = data;
    }
  });

fetch("components/footer.html")
	.then(res => res.text())
	.then(data => {
	const navbar = document.getElementById("footer");
	if (navbar) {
    	navbar.innerHTML = data;
    }
});