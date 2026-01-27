console.log('register.js');

document.getElementById("registerForm").onsubmit = async (e) => {
  e.preventDefault();
  const form = e.target;
  const password = form.elements.password.value;
  const reTypedPassword = form.elements.re-password.value;

  if (password.localeCompare(reTypedPassword) == -1) {
	console.log("passwords doesn't match");
  }

  const formParams = Object.fromEntries(new FormData(e.target));
  const body =	{
					email: formParams.email,
					firstName: formParams.firstName,
					lastName: formParams.lastName,
					password: formParams.password
  				}
  console.log(body);

  const res = await fetch("http://localhost:9090/api/auth/register", {
    method: "POST",
	body: JSON.stringify(body),
	credentials: "include",
	headers: { "Content-Type": "application/json" }
  });
  if (res.ok) {
    window.location.href = "/login";
  } else {
	console.log('Response: ' + res);
  }
};
