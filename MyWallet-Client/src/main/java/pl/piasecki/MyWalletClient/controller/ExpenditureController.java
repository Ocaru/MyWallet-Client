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
public class ExpenditureController {

	private User[] userTab;
	private ExpenditureCategory[] expenditureCategoryTab;
	private Expenditure[] expenditureTab;
	
	@Autowired
	private RestClient rc;
	private LoginDTO ld;
	
	
	@RequestMapping("/")
	public String showLoginPage(Model model) {
		
		model.addAttribute("loginCredentials", ld);
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

		rc.authorization(loginDtoJSON);
		
		addExpendituresToModel(model);
		addUsersToModel(model);
		addCategoriesToModel(model);
		addNewExpenditureToModel(model);
		
		return "index";

	}
	
	
	@RequestMapping("/showExpenditures")
	public String showExpenditures(Model model) {
		
		addExpendituresToModel(model);
		addUsersToModel(model);
		addCategoriesToModel(model);
		addNewExpenditureToModel(model);
		
		return "index";

	}
	
	
	@RequestMapping("/saveExpenditure")
	public String saveNewExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		String expenditureJson = mapToJson(expenditure);
		rc.post("/expenditures", expenditureJson);
		return "redirect:/showExpenditures";
	}
	
	@RequestMapping("/updateExpenditure")
	public String updateExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		String expenditureJson = mapToJson(expenditure);
		rc.put("/expenditures", expenditureJson);
		return "redirect:/showExpenditures";
	}
	
	
	@RequestMapping("/setExpenditureToUpdate")
	public String setExpenditureToUpdate(@ModelAttribute("expenditure") Expenditure expenditure, Model model )
	{
		 Expenditure[] exps = rc.getExpenditures("/expenditures");
		 for (Expenditure exp : exps) {
			if(exp.getId() == expenditure.getId())
				model.addAttribute("expenditureToUpdate", exp);
		}

		addCategoriesToModel(model);
		 
		return "/expenditureUpdatePage";
	}
	
	
	@RequestMapping("/deleteExpenditure")
	public String deleteExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		rc.delete("/expenditures/" + expenditure.getId());
		return "redirect:/showExpenditures";
	}
	
	
	@RequestMapping("/filterByUser")
	public String sortByUser(@ModelAttribute("user") User user, Model model )
	{
		Expenditure[] expenditureTab = rc.getExpenditures("/expenditures/user_id=" + user.getId());
		model.addAttribute("expenditureTab", expenditureTab);
		 
		addUsersToModel(model);
		addCategoriesToModel(model);
		addNewExpenditureToModel(model);
		 
		return "index";
	}
	
	
	private void addUsersToModel(Model model)
	{
		userTab = rc.getUsers("/users");
		model.addAttribute("userList", userTab);
	}
	
	
	private void addCategoriesToModel(Model model)
	{
		expenditureCategoryTab = rc.getExpenditureCategory("/expenditureCategories");
		model.addAttribute("expenditureCategoryTab", expenditureCategoryTab);
	}
	
	
	private void addNewExpenditureToModel(Model model)
	{
		Expenditure expenditure = new Expenditure();
		model.addAttribute("expenditure", expenditure);
	}
	
	
	private void addExpendituresToModel(Model model)
	{
		expenditureTab = rc.getExpenditures("/expenditures");
		model.addAttribute("expenditureTab", expenditureTab);
	}
	
	
	private String mapToJson(Expenditure expenditure)
	{
		JsonMapper mapper = new JsonMapper();
		try {
			String json = mapper.writeValueAsString(expenditure);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
