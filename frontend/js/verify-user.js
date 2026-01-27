
function fillEmail() {
	const urlParams = new URLSearchParams(window.location.search);
	const myParam = urlParams.get('email');
	if (myParam == null) {
		console.log('email not found -- Should do smth about it');
		document.getElementById("verification-email-id").innerHTML = "randomemail@random.com";
		return "";
	}
	console.log("myParam: " + myParam);
	document.getElementById("verification-email-id").innerHTML = myParam;
	
	return myParam;
}

const userEmail = fillEmail();

document.getElementById('resend-code-button').onclick = async (e) => {
	e.preventDefault();
	console.log("EMAIL: " + userEmail);
	const res = await fetch("http://localhost:9090/api/auth/verification/generate-code", {
		method: "POST",
		body: JSON.stringify({email: userEmail}),
		credentials: "include",
		headers: { "Content-Type": "application/json" }
  	});
	console.log('response code: ' + res.status);
	if (!res.ok)
		window.location.href = "/";
};

document.getElementById("verifyUserForm").onsubmit = async (e) => {
  e.preventDefault();

  const body =	{
					email: userEmail,
					code: e.target.code.value
  				}
  console.log(body);

  const res = await fetch("http://localhost:9090/api/auth/verification/verify-email", {
    method: "POST",
	body: JSON.stringify(body),
	credentials: "include",
	headers: { "Content-Type": "application/json" }
  });
  console.log("Body.email -> " + body.email);
   if (res.ok) {
	window.location.href = "/login";
  }
};
