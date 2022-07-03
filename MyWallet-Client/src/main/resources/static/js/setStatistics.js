
function setStatistics() {
	buildTabelMoneyByCategory();
	buildTabelMoneyByMonth();

}


function buildTabelMoneyByCategory() {
	let table = document.createElement('table');
	let thead = document.createElement('thead');
	let tbody = document.createElement('tbody');
	table.className = "statisticsTable";

	table.appendChild(thead);
	table.appendChild(tbody);

	document.getElementById('tableMoneyByCategory').appendChild(table);

	let row_1 = document.createElement('tr');
	let heading_1 = document.createElement('th');
	heading_1.innerHTML = "CATEGORY";
	let heading_2 = document.createElement('th');
	heading_2.innerHTML = "MONEY SPENT";

	row_1.appendChild(heading_1);
	row_1.appendChild(heading_2);
	thead.appendChild(row_1);


	var categories = [];
	categories = getCategories();

	var expenditures = [];
	expenditures = getExpenditures();

	let categoryName = [];
	let moneyByCat = [];
	let i = 0;

	let moneyByCategory;
	for (let category of categories) {

		moneyByCategory = 0;
		for (let exp of expenditures) {
			if (exp.category_id == category.id)
				moneyByCategory += exp.money;
		}

		let row = document.createElement('tr');
		let row_data_1 = document.createElement('td');
		row_data_1.innerHTML = category.name;
		let row_data_2 = document.createElement('td');
		row_data_2.innerHTML = moneyByCategory.toFixed(2);

		categoryName[i] = category.name;
		moneyByCat[i] = moneyByCategory.toFixed(2);
		i++;

		row.appendChild(row_data_1);
		row.appendChild(row_data_2);
		tbody.appendChild(row);
	}

	createChartMoneyByCategory(categoryName, moneyByCat)
}



function buildTabelMoneyByMonth() {
	let table = document.createElement('table');
	let thead = document.createElement('thead');
	let tbody = document.createElement('tbody');
	table.className = "statisticsTable";

	table.appendChild(thead);
	table.appendChild(tbody);

	document.getElementById('tableMoneyByMonth').appendChild(table);

	let row_1 = document.createElement('tr');
	let heading_1 = document.createElement('th');
	heading_1.innerHTML = "MONTH";
	let heading_2 = document.createElement('th');
	heading_2.innerHTML = "MONEY SPENT";

	row_1.appendChild(heading_1);
	row_1.appendChild(heading_2);
	thead.appendChild(row_1);


	var expenditures = [];
	expenditures = getExpenditures();

	let totalMoneySpend = 0;

	let monthNames = [];
	let money = [];
	let j = 0;

	for (let exp of expenditures) {
		totalMoneySpend += exp.money;
	}
	//console.log(totalMoneySpend);
	//alert(totalMoneySpend);

	let row_2 = document.createElement('tr');
	let data_1 = document.createElement('th');
	data_1.innerHTML = "All";
	let data_2 = document.createElement('th');
	data_2.innerHTML = totalMoneySpend.toFixed(2);

	row_2.appendChild(data_1);
	row_2.appendChild(data_2);
	tbody.appendChild(row_2);

	var expenditures = [];
	expenditures = getExpenditures();


	for (let i = 0; i < 12; i++) {
		moneyByMonth = 0;
		for (let exp of expenditures) {

			let stringTab = [];
			stringTab = exp.date.split("-");

			if (stringTab[1] == "0" + i || stringTab[1] == i)
				moneyByMonth += exp.money;
		}

		if (moneyByMonth != 0) {

			let row = document.createElement('tr');
			let row_data_1 = document.createElement('td');
			row_data_1.innerHTML = i;
			let row_data_2 = document.createElement('td');
			row_data_2.innerHTML = moneyByMonth.toFixed(2);


			row.appendChild(row_data_1);
			row.appendChild(row_data_2);
			tbody.appendChild(row);

			monthNames[j] = i;
			money[j] = moneyByMonth.toFixed(2);
			j++;

		}
	}

	createChartMoneyByMonth(monthNames, money);

}
