package pl.piasecki.MyWalletClient.model;

public class ExpenditureCategory {

	private long id;
	private String name;

	public ExpenditureCategory() {
		
	}

	public ExpenditureCategory(long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "ExpenditureCategory [id=" + id + ", name=" + name + "]";
	}
}
