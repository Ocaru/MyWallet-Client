package pl.piasecki.Wallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pl.piasecki.Wallet.model.User;

@Controller
public class HomeController {
	
	@Autowired
	final ExpenditureController expenditureController;
	final UserController userController;
	
	public HomeController(ExpenditureController expenditureController, UserController userController) {
		this.expenditureController = expenditureController;
		this.userController = userController;
	}


	@RequestMapping("/")
	public String index(Model model)
	{
		String uri = "http://localhost:8080/users";
		RestTemplate restTemplate = new RestTemplate();
		User[] userTable = restTemplate.getForObject(uri, User[].class);
		
		model.addAttribute("expenditureList", expenditureController.getExpenditures());	
		model.addAttribute("userList", userTable);	
		return "index";
	}
	

}
