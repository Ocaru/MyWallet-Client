package pl.piasecki.MyWalletClient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Role {

	private long id;
	private String name;

	@JsonIgnoreProperties("role")
	private Set<User> users; 

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
	
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void addUser(User user)
	{
		users.add(user);
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users id=" + getUsersId().toString() + "]";
	}

	private List<Long> getUsersId()
	{
		List<Long> userIdList = new ArrayList<Long>();
		
		for (User user : users) {
			userIdList.add(user.getId());
		}
		
		return userIdList;
	}

}

