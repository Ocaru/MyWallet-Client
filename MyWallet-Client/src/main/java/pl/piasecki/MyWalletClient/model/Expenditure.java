package pl.piasecki.MyWalletClient.model;


public class Expenditure {

	private long id;
	private String name;
	private float money;
	private String description;
	private long user_id;
	
	public Expenditure() {}
	
	public Expenditure(String name, float money, String description, long user_id) {
		this.name = name;
		this.money = money;
		this.description = description;
		this.user_id = user_id;
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
