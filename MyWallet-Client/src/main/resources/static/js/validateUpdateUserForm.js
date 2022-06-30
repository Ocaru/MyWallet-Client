function validateUpdateUserForm()
{
	var users = getUsers();
	var userToUpdate = getUserToUpdate();
	let usernameString = document.getElementById("username").value;
	let usernameInput = document.getElementById("username");
	let flag = 0;
	
	for ( let user of users) {
		
		if( usernameString == user.username && usernameString != userToUpdate.username)
			flag = 1;
	}
	
	
	if(flag == 1)
	{
		alert("This username is already taken");
		usernameInput.style.border = "3px solid RED";
		return false;
	}
	else 
	{
		usernameInput.style.border = "2px solid GREEN";
		return true;
	}
	
}