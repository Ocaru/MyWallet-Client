package pl.piasecki.Wallet.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expenditure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private float money;
	private String description;
	//private LocalDateTime createdTime;
	
	public Expenditure() {}
	
	public Expenditure( String name, float money, String description) {
		this.name = name;
		this.money = money;
		this.description = description;
	}


	@Override
	public boolean equals(Object o) {
		
		float epsilon = 0.00000001f;
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Expenditure expenditure = (Expenditure) o;
		return Long.compare(id, expenditure.id) == 0 &&
			Objects.equals(name, expenditure.name) &&
			Math.abs(money - expenditure.money) < epsilon &&
			Objects.equals(description, expenditure.description);
	}
	
	
	@Override
	public int hashCode() {

		return Objects.hash(id, name, description);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public float getMoney() {
		return money;
	}


	public void setMoney(float money) {
		this.money = money;
	}


	@Override
	public String toString() {
		return "Expenditure [id=" + id + ", name=" + name + ", money=" + money + ", description=" + description + "]";
	}


	
	
	
	
	
	
}
