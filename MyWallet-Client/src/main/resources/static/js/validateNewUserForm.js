function validateNewUserForm() {
	var users = getUsers();
	let usernameString = document.getElementById("username").value;
	let usernameInput = document.getElementById("username");
	let flag = 0;

	for (let user of users) {

		if (user.username == usernameString)
			flag = 1;
	}

	if (flag == 1) {
		alert("This username is already taken");
		usernameInput.style.border = "2px solid RED";
		return false;
	}
	else {
		usernameInput.style.border = "2px solid GREEN";
		return true;
	}

}