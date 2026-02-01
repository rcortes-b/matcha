function getSelectedTags() {
  const checkedBoxes = document.querySelectorAll(
    'input[name="tags"]:checked'
  );

  return Array.from(checkedBoxes).map(tag => tag.value);
}

document.getElementById('completeProfileForm').onsubmit = async (e) => {
	e.preventDefault();

	const birthDate = document.getElementById('birthday-input').value;
	const gender = document.getElementById('gender').value;
	const sexualPreference = document.getElementById('sexualPreference').value;
	const biography = document.getElementById('complete-biography').value;
	const tags = getSelectedTags();
	if (tags.length < 3)
		console.log("MALAMENT NOI!");

	const body =	{
						birthDate: birthDate,
						gender: gender,
						sexualPreference: sexualPreference,
						biography: biography
					};
	console.log('Request will look like this:\n', body);
	console.log('tags as list are:\n', tags);
	
	const res = await fetch("http://localhost:8082/api/users/complete-profile", {
		method: "POST",
		credentials: 'include',
		body: JSON.stringify(body),
		headers: { "Content-Type": "application/json" }
	});

	if (res.ok) {
		window.location.href = "/";
	} else {
	
	}
};