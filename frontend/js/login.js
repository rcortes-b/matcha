await window.componentsLoaded;

if (localStorage.getItem('user-connected'))
	window.location.href = '/';

document.getElementById('navlinks').style.display = 'flex';


document.getElementById("loginForm").onsubmit = async (e) => {
  e.preventDefault();

  const body = Object.fromEntries(new FormData(e.target));
  console.log(body);

  const res = await fetch("http://localhost:9090/api/auth/login", {
    method: "POST",
	body: JSON.stringify(body),
	credentials: "include",
	headers: { "Content-Type": "application/json" }
  });
  console.log("Body.email -> " + body.email);
  if (res.status == 401) {
	window.location.href = "/verify-user?email=" + body.email;
  } else if (res.ok) {
		localStorage.setItem('user-connected', 'connected');
		window.location.href = "/complete-profile";
  }
};
