/*
package pl.piasecki.Wallet.configuration;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.piasecki.Wallet.model.Expenditure;   
import pl.piasecki.Wallet.model.User;
import pl.piasecki.Wallet.repository.ExpenditureRepository;
import pl.piasecki.Wallet.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final ExpenditureRepository expenditureRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public DatabaseLoader(ExpenditureRepository expenditureRepository, UserRepository userRepository) {
		this.expenditureRepository = expenditureRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		
		System.out.println("ODPALENIE RUN() W DATABASE LOADER");
		expenditureRepository.save(new Expenditure("Grocery shopping", 50.30f, "Shopping in Biedra"));
		expenditureRepository.save(new Expenditure("Food1", 15.00f, "Kebab"));
		expenditureRepository.save(new Expenditure("Food2", 120.60f, "Two big pizzas"));
		expenditureRepository.save(new Expenditure("Car", 12600f, "New car"));
		expenditureRepository.save(new Expenditure("Ice-cream", 5f, "Two ice-creams"));
		expenditureRepository.save(new Expenditure("Course", 1000f, "Course to learn programming "));
		
		userRepository.save(new User("Kamil", "Piasecki", "kamil.piasecki14@gmail.com"));
		userRepository.save(new User("Diana", "Duda", "diana_xyz@gmail.com"));


	}

}
*/
