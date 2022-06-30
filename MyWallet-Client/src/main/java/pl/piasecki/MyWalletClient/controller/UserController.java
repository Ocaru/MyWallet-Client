package pl.piasecki.MyWalletClient.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.Role;
import pl.piasecki.MyWalletClient.model.User;

@Controller
public class UserController {

	private User[] userTab;
	private Role[] roleTab;
	@Autowired
	private RestClient rc;
	

	@RequestMapping("/userPage")
	public String showUserPage( Model model)
	{
		
		userTab = rc.getUsers("/users");
		model.addAttribute("userList", userTab);
	
		User theUser = new User();
		model.addAttribute("user", theUser);
		
		roleTab = rc.getRoles("/roles");
		model.addAttribute("roleTab", roleTab);
		
		addLoggedInUserToModel(model);
		
		return "userPage";
	}

	
	@RequestMapping("/saveUser")
	public String saveNewUser(@ModelAttribute("user") User user)
	{
		String json = getJson(user); 
		rc.post("/users", json);				//SAVE USER
		
		roleTab = rc.getRoles("/roles");		//GET Roles
		User tempUser = rc.getUser("/users/username=" + user.getUsername()); //GET USER
		
		if(user.getIsAdmin() == 1 ){
			tempUser.addRole(roleTab[0]);
			}
		
		tempUser.addRole(roleTab[1]);
		
		json = getJson(tempUser);
		rc.put("/users", json);

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
		addLoggedInUserToModel(model);
		return "updateUser";
	}
	
	
	
	@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user )
	{
		String userJSON = getJson(user);
		rc.put("/users", userJSON);
		return "redirect:/userPage";
	}
	

	@RequestMapping("/deleteUser")
	public String deleteUser(@ModelAttribute("user") User user )
	{
		rc.delete("/users/" +  user.getId());
		return "redirect:/userPage";
	}

	
	
	private String getJson(User user)
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = ""; 
		try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json; 
	}
	
	
	private void addLoggedInUserToModel(Model model)
	{
		model.addAttribute("loggedInUser", rc.getUser("/users/loggedIn"));
	}
	
	
}
