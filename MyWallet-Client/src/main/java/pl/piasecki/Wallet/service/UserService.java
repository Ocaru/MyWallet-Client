package pl.piasecki.Wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.piasecki.Wallet.model.User;
import pl.piasecki.Wallet.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers()
	{
		return userRepository.findAllUsers();
	}
	
	public User getUser(long id)
	{
		return userRepository.findById(id).orElseThrow();
	}
	
	
}
