function createChartMoneyByCategory(xCategories, yMoney) {
	var fileref = document.createElement('script');
	fileref.setAttribute("src", "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js");

	Chart.defaults.global.defaultFontColor = "000";
	Chart.defaults.global.legend.position = 'right';

	var barColors = ["#fcf80d", "#fca40d", "#fc0d0d", "#acfc0d", "#109e0b", "#05f595", 
	"#05e9f5", "#09a3e6", "#8a09e6", "#e681e4", "#57351e", "#706f6e"];

	new Chart("chartMoneyByCategory", {
		type: "doughnut",
		data: {
			labels: xCategories,
			datasets: [{
				backgroundColor: barColors,
				data: yMoney
			}]
		},
		options: {
			title: {
				display: true,

				text: "Money spent by category"
			}
		}
	});
}

function createChartMoneyByMonth(xMonth, yMoney) {


	var barColors = ["#fcf80d", "#fca40d", "#fc0d0d", "#acfc0d", "#109e0b", "#05f595", 
	"#05e9f5", "#09a3e6", "#8a09e6", "#e681e4", "#57351e", "#706f6e"];

	new Chart("chartMoneyByMonth", {
		type: "bar",
		data: {
			labels: xMonth,
			datasets: [{
				backgroundColor: barColors,
				data: yMoney
			}]
		},
		options: {
			legend: { display: false },
			title: {
				display: true,
				text: "Money spent by month"
			}
		}
	});
}




