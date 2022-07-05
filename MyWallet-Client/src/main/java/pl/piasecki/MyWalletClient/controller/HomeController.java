package pl.piasecki.MyWalletClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.configuration.LoginDTO;
import pl.piasecki.MyWalletClient.model.Expenditure;
import pl.piasecki.MyWalletClient.model.ExpenditureCategory;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class HomeController {

	private User[] userTab;
	private ExpenditureCategory[] expenditureCategoryTab;
	private Expenditure[] expenditureTab;

	@Autowired
	private RestClient restClient;;
	
	@RequestMapping("/")
	public String showLoginPage(Model model) {
		model.addAttribute("loginCredentials", new LoginDTO());

		return "login";
	}

	@RequestMapping("/home")
	public String login(@ModelAttribute("loginDTO") LoginDTO loginDto, Model model) {
		JsonMapper mapper = new JsonMapper();
		String loginDtoJSON = "";
		try {
			loginDtoJSON = mapper.writeValueAsString(loginDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		restClient.authorization(loginDtoJSON);

		addExpendituresToModel(model);
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
}
