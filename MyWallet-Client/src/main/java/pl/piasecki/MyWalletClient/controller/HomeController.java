package pl.piasecki.MyWalletClient.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.Expenditure;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(Model model) {
		RestClient rc = new RestClient();

		List<User> userList = rc.get("/users", User.class);
		List<Expenditure> expenditureList = rc.get("/expenditures", Expenditure.class);

		model.addAttribute("expenditureList", expenditureList);
		model.addAttribute("userList", userList);

		return "index";

	}

}
