package pl.piasecki.Wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.piasecki.Wallet.model.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

	
	@Query("SELECT e FROM Expenditure e")
	List<Expenditure> findAllExpenditure();
}
