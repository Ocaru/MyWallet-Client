package pl.piasecki.MyWalletClient.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.User;


@Controller
public class HomeController {
	



	@RequestMapping("/")
	public String index(Model model)
	{
		RestClient rc = new RestClient();
		String response = rc.get("/users");
		List<User> userList = rc.parseFromGet2(response);
		
		model.addAttribute("userList", userList);

		return "index";
	}
	

}
