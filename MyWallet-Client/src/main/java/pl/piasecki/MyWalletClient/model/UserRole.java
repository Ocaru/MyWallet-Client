package pl.piasecki.MyWalletClient.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserRole {
	
	private long id;
	
	private long idUser;
	private long idRole;
	
	
	private User user;
	
	
	private Role role;

	public UserRole() {}
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
		idUser = user.getId();
		idRole = role.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", idUser=" + idUser + ", idRole=" + idRole + "]";
	}
	
}

