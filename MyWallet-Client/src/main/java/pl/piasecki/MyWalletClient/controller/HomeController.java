package pl.piasecki.MyWalletClient.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.Expenditure;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class HomeController {

	private List<User> userList;
	//private List<Expenditure> expenditureList;
	private Expenditure[] expenditureTab;
	private RestClient rc = new RestClient();
	
	@RequestMapping("/")
	public String showHomePage(Model model) {
		//expenditureList = rc.get("/expenditures", Expenditure.class);
		
		expenditureTab = rc.getExpenditures("/expenditures");
		model.addAttribute("expenditureList", expenditureTab);
		
		userList = rc.get("/users", User.class);
		model.addAttribute("userList", userList);
		
		Expenditure expenditure = new Expenditure();
		model.addAttribute("expenditure", expenditure);

		return "index";

	}
	
	@RequestMapping("/userPage")
	public String showUserPage(Model model)
	{
		
		userList = rc.get("/users", User.class);
		model.addAttribute("userList", userList);
		
		User theUser = new User();
		model.addAttribute("user", theUser);
	
		return "userPage";
	}
	
	@RequestMapping("/saveExpenditure")
	public String saveNewExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		JsonMapper mapper = new JsonMapper();
		String expenditureJSON = ""; 
		try {
			expenditureJSON = mapper.writeValueAsString(expenditure);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		rc.post("/expenditures", expenditureJSON);
		
		return "redirect:/";
	}
	
	@RequestMapping("/saveUser")
	public String saveNewUser(@ModelAttribute("user") User user)
	{
		JsonMapper mapper = new JsonMapper();
		String userJSON = ""; 
		try {
			userJSON = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		rc.post("/users", userJSON);
		
		return "redirect:/userPage";
	}
	
	

	
	@RequestMapping("/updateExpenditure")
	public String updateExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		JsonMapper mapper = new JsonMapper();
		String expenditureJSON = "";
		try {
			expenditureJSON = mapper.writeValueAsString(expenditure);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		rc.put("/expenditures", expenditureJSON);
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/setExpenditureToUpdate")
	public String setExpenditureToUpdate(@ModelAttribute("expenditure") Expenditure expenditure, Model model )
	{
		 Expenditure[] exps = rc.getExpenditures("/expenditures");
		 
		 for (Expenditure exp : exps) {
				if(exp.getId() == expenditure.getId())
				{
					model.addAttribute("expenditureToUpdate", exp);
				} 
			
		}
		 
		return "/expenditureUpdatePage";
	}
	
	@RequestMapping("/deleteExpenditure")
	public String deleteExpenditure(@ModelAttribute("expenditure") Expenditure expenditure )
	{
		
		 Expenditure[] exps = rc.getExpenditures("/expenditures");
		 Expenditure tempExpenditure = new Expenditure();
		 
		 for (Expenditure exp : exps) {
				if(exp.getId() == expenditure.getId())
				{
					tempExpenditure = exp;
					break;
				} 
		}
		
		rc.delete("/expenditures/" + tempExpenditure.getId());
		
		return "redirect:/";
	}
	
	

}
