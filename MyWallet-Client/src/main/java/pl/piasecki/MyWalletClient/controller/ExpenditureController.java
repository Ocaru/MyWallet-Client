package pl.piasecki.MyWalletClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.Expenditure;
import pl.piasecki.MyWalletClient.model.ExpenditureCategory;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class ExpenditureController {

	private User[] userTab;
	private ExpenditureCategory[] expenditureCategoryTab;
	private Expenditure[] expenditureTab;

	@Autowired
	private RestClient restClient;;

	@RequestMapping("/showExpenditures")
	public String showExpenditures(Model model) {
		addExpendituresToModel(model);
		addUsersToModel(model);
		addCategoriesToModel(model);
		addNewExpenditureToModel(model);
		addLoggedInUserToModel(model);

		return "home";
	}

	@RequestMapping("/saveExpenditure")
	public String saveNewExpenditure(@ModelAttribute("expenditure") Expenditure expenditure) {
		String expenditureJson = mapToJson(expenditure);
		restClient.post("/expenditures", expenditureJson);
		return "redirect:/showExpenditures";
	}

	@RequestMapping("/updateExpenditure")
	public String updateExpenditure(@ModelAttribute("expenditure") Expenditure expenditure) {
		String expenditureJson = mapToJson(expenditure);
		restClient.put("/expenditures", expenditureJson);

		return "redirect:/showExpenditures";
	}

	@RequestMapping("/setExpenditureToUpdate")
	public String setExpenditureToUpdate(@ModelAttribute("expenditure") Expenditure expenditure, Model model) {
		Expenditure[] exps = restClient.getExpenditures("/expenditures");
		for (Expenditure exp : exps) {
			if (exp.getId() == expenditure.getId()) {
				model.addAttribute("expenditureToUpdate", exp);
			}
		}

		addCategoriesToModel(model);
		addLoggedInUserToModel(model);

		return "/expupdate";
	}

	@RequestMapping("/deleteExpenditure")
	public String deleteExpenditure(@ModelAttribute("expenditure") Expenditure expenditure) {
		restClient.delete("/expenditures/" + expenditure.getId());

		return "redirect:/showExpenditures";
	}

	@RequestMapping("/filterByUser")
	public String sortByUser(@ModelAttribute("user") User user, Model model) {
		Expenditure[] expenditureTab = restClient.getExpenditures("/expenditures/user_id=" + user.getId());
		model.addAttribute("expenditureTab", expenditureTab);

		addUsersToModel(model);
		addCategoriesToModel(model);
		addNewExpenditureToModel(model);
		addLoggedInUserToModel(model);

		return "home";
	}

	private void addUsersToModel(Model model) {
		userTab = restClient.getUsers("/users");
		model.addAttribute("userList", userTab);
	}

	private void addCategoriesToModel(Model model) {
		expenditureCategoryTab = restClient.getExpenditureCategory("/expenditureCategories");
		model.addAttribute("expenditureCategoryTab", expenditureCategoryTab);
	}

	private void addNewExpenditureToModel(Model model) {
		Expenditure expenditure = new Expenditure();
		model.addAttribute("expenditure", expenditure);
	}

	private void addExpendituresToModel(Model model) {
		expenditureTab = restClient.getExpenditures("/expenditures");
		model.addAttribute("expenditureTab", expenditureTab);
	}

	private void addLoggedInUserToModel(Model model) {
		model.addAttribute("loggedInUser", restClient.getUser("/users/loggedIn"));
	}

	private String mapToJson(Expenditure expenditure) {
		JsonMapper mapper = new JsonMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(expenditure);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

}
