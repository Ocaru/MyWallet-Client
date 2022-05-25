package pl.piasecki.Wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piasecki.Wallet.model.Expenditure;
import pl.piasecki.Wallet.service.ExpenditureService;


@RestController
public class ExpenditureController {

	@Autowired
	private ExpenditureService expenditureService;
	
	@GetMapping("/expenditures")
	public List<Expenditure> getExpenditures()
	{
		return expenditureService.getExpenditures();
	}
	
	@GetMapping("/expenditures/{id}")
	public Expenditure getSingleExpenditure(@PathVariable long id)
	{
		return expenditureService.getExpenditure(id);
	}
	
	
	@PostMapping("/expenditures")
	public Expenditure addPost(@RequestBody Expenditure expenditure)
	{
		return expenditureService.addExpenditure(expenditure);
	}
	
	@PutMapping("/expenditures")
	public Expenditure editExpenditure(@RequestBody Expenditure expenditure)
	{
		return expenditureService.editExpenditure(expenditure);
	}
	
	@DeleteMapping("/expenditures/{id}")
	public void deletePost(@PathVariable long id)
	{
		expenditureService.deleteExpenditure(id);
	}
}
