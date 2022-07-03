function setDateToForm() {
	let date = new Date();
	let day = date.getDate();
	let month = date.getMonth() + 1;
	let year = date.getFullYear();
	let dateString = "";

	dateString += year + "-";

	if (month < 10) {
		dateString += "0" + month + "-";
	}
	else {
		dateString += month + "-";
	}

	if (day < 10){
		dateString += "0" + day;
	}
	else {
	dateString += day;
	}

	document.getElementById("dateInput").defaultValue = dateString;

}