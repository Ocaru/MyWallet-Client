    function getTime()
	{
		let date  = new Date();

		let hour = date.getHours();
		let minute = date.getMinutes();
		let second = date.getSeconds();
		let timeString = "";
		
		if(hour < 10) timeString += "0"+hour+":";
		else timeString += hour+":";
		
		if(minute < 10) timeString += "0" + minute+":";
		else timeString += minute+":";
		
		if(second < 10) timeString += "0" + second;
		else timeString += second;

		document.getElementById("clock").innerHTML =  timeString;
		
		
		let day = date.getDate();
		let month = date.getMonth()+1;
		let year = date.getFullYear();
		let dateString = "";
		
		
		if(day < 10) dateString += "0"+day+"/";
		else dateString += day+"/";
		
		if(month < 10) dateString += "0" + month+"/";
		else dateString += month+"/";
		
		dateString += year;

		document.getElementById("date").innerHTML =  dateString;	
					
		setTimeout("getTime()", 1000)
	}