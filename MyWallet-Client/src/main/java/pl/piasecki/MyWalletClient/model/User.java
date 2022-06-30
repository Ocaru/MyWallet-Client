package pl.piasecki.MyWalletClient.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class User {

	private long id;
	private String name;
	private String surname; 
	private String username;
	private String password;
	private String email;
	
	@JsonIgnore
	private int isAdmin = 0;
	
	@JsonIgnore
	private List<Expenditure> expenditureList;
	
	@JsonIgnoreProperties("users")
	private Set<Role> roles = new HashSet<Role>();

	public User() {}
	
	public User(String name, String surname, String username, 
			String password, String email, Set<Role> roles) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	
    public void addRole(Role role) {
        roles.add(role);
        role.addUser(this);
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
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Expenditure> getExpenditureList() {
		return expenditureList;
	}

	public void setExpenditureList(List<Expenditure> expenditureList) {
		this.expenditureList = expenditureList;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + ", email=" + email + ", expenditureList=" + expenditureList + ", roles=" + getRolesId().toString()
				+ "]";
	}
	
	private List<Long> getRolesId()
	{
		List<Long> roleIdList = new ArrayList<Long>();
		
		for (Role role : roles) {
			roleIdList.add(role.getId());
		}
		
		return roleIdList;
	}
	
}
