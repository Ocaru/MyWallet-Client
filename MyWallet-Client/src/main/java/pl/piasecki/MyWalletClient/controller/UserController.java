package pl.piasecki.MyWalletClient.controller;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import pl.piasecki.MyWalletClient.RestClient;
import pl.piasecki.MyWalletClient.model.Role;
import pl.piasecki.MyWalletClient.model.User;
import pl.piasecki.MyWalletClient.model.UserRole;

@Controller
public class UserController {

	private User[] userTab;
	private Role[] roleTab;
	private UserRole[] userRoles;
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

	
		

		
		return "userPage";
	}

	
	@RequestMapping("/saveUser")
	public String saveNewUser(@ModelAttribute("user") User user)
	{
		String json = getJson(user); 
		
		rc.post("/users", json);				//SAVE USER
		
		roleTab = rc.getRoles("/roles");		//GET Roles
		user = rc.getUser("/users/username=" + user.getUsername()); //GET USER
		UserRole userRole = new UserRole(user, roleTab[1]);
		
		json = getJson(userRole);
		rc.post("/userRoles", json);		//SAVE UserRole
		
		UserRole[] userRolesTab = rc.getUserRoles("/userRoles");		//GET UserRoles
		
		for (UserRole uRole : userRolesTab) {
			if(uRole.getIdUser() == userRole.getIdUser() && uRole.getIdRole() == userRole.getIdRole())
				userRole = uRole;
		}

		userRole.setUser(user);
		userRole.setRole(roleTab[1]);	
		
		json = getJson(userRole);
		rc.put("/userRoles", json);
		
		Set<UserRole> userRoleSet = new HashSet<UserRole>();
		userRoleSet.add(userRole);
		
		user.setUserRoles(userRoleSet);
		
		json = getJson(user);
		rc.put("/users", json);

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
	
	private String getJson(UserRole userRole)
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = ""; 
		try {
			json = mapper.writeValueAsString(userRole);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json; 
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

		rc.delete("/users/" +  user.getId());
		
		return "redirect:/userPage";
	}
	
}
