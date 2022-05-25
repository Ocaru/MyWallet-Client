package pl.piasecki.Wallet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.piasecki.Wallet.exception.ExpenditureNotFoundException;
import pl.piasecki.Wallet.model.Expenditure;
import pl.piasecki.Wallet.repository.ExpenditureRepository;


@Service
public class ExpenditureService {

	@Autowired
	private ExpenditureRepository expenditureRepository;
	
	public List<Expenditure> getExpenditures()
	{
		return expenditureRepository.findAllExpenditure();
	}
	
	public Expenditure getExpenditure(long id)
	{
		return expenditureRepository.findById(id).orElseThrow(() -> new ExpenditureNotFoundException(id));
	}
	

	public Expenditure addExpenditure(Expenditure expenditure) {
		
		return expenditureRepository.save(expenditure);
	}

	@Transactional //zeby zamiast dwoch transakcji wykonalo sie w jednej transakcji
	public Expenditure editExpenditure(Expenditure expenditure)
	{
		Expenditure expenditureEdited = expenditureRepository.findById(expenditure.getId()).orElseThrow();
		expenditureEdited.setName(expenditure.getName());
		expenditureEdited.setMoney(expenditure.getMoney());
		expenditureEdited.setDescription(expenditure.getDescription());
		return expenditureEdited;
	}

	public void deleteExpenditure(long id) {
		expenditureRepository.deleteById(id);
		
	}
	
	
	
	
}

