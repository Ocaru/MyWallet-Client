package pl.piasecki.MyWalletClient.model;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Role {


	private long id;
	private String name;
	
	@JsonIgnoreProperties("role")
	private Set<UserRole> userRoles; 

	public Role() {
	}

	public Role(String name) {
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
	
	 
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", userRoles=" + userRoles + "]";
	}



}

