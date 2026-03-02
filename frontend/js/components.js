async function loadComponent(id, file) {
  const res = await fetch(file);
  const data = await res.text();

  const element = document.getElementById(id);
  if (element) element.innerHTML = data;
}

async function loadComponents() {
  await loadComponent("navbar", "components/navbar.html");
  await loadComponent("footer", "components/footer.html");
}

window.componentsLoaded = loadComponents();