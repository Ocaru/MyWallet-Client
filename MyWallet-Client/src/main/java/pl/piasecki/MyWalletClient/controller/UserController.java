package pl.piasecki.MyWalletClient.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class UserController {

	private List<User> userList;
	private RestClient rc = new RestClient();
	
	@RequestMapping("/userPage")
	public String showUserPage(Model model)
	{
		
		userList = rc.get("/users", User.class);
		model.addAttribute("userList", userList);
		
		User theUser = new User();
		model.addAttribute("user", theUser);
	
		return "userPage";
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
	
	
	
	
	@RequestMapping("/setUserToUpdate")
	public String setUserToUpdate(@ModelAttribute("user") User user, Model model )
	{
		 User[] users = rc.getUsers("/users");
		 
		 for (User u : users) {
				if(u.getId() == user.getId())
				{
					model.addAttribute("userToUpdate", u);
				} 
			
		}
		 
		return "updateUser";
	}
	
	
	
	@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user )
	{
		JsonMapper mapper = new JsonMapper();
		String userJSON = "";
		try {
			userJSON = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		rc.put("/users", userJSON);
		
		return "redirect:/userPage";
	}
	
	

	
	@RequestMapping("/deleteUser")
	public String deleteUser(@ModelAttribute("user") User user )
	{
		
		 User[] users = rc.getUsers("/users");
		 User tempUser = new User();
		 
		 for (User u : users) {
				if(u.getId() == user.getId())
				{
					tempUser = u;
					break;
				} 
		}
		
		rc.delete("/users/" + tempUser.getId());
		
		return "redirect:/userPage";
	}
	
}
