package pl.piasecki.MyWalletClient.model;


import java.math.BigDecimal;
import java.sql.Date;


public class Expenditure {

	private long id;
	private String name;
	private BigDecimal money;
	private Date date;
	private String description;
	private long user_id;
	private long category_id;
	
	public Expenditure() {}
	
	public Expenditure(String name, BigDecimal money, Date date, 
			String description, long user_id, long category_id) {
		this.name = name;
		this.money = money;
		this.date = date;
		this.description = description;
		this.user_id = user_id;
		this.category_id=category_id;
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
	
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "Expenditure [id=" + id + ", name=" + name + ", money=" + money + ", date=" + date + ", description="
				+ description + ", user_id=" + user_id + ", category_id=" + category_id + "]";
	}
	
}
