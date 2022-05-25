package pl.piasecki.Wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.piasecki.Wallet.model.User;
import pl.piasecki.Wallet.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getSingleUser(@PathVariable long id)
	{
		return userService.getUser(id);
	}
	
}
