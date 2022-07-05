package pl.piasecki.MyWalletClient;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
 
import pl.piasecki.MyWalletClient.configuration.LoginDTO;
import pl.piasecki.MyWalletClient.model.Role;
import pl.piasecki.MyWalletClient.model.User;

@SpringBootTest()
public class RestClientTests {

	private HttpEntity<String> requestEntity;
	
	private RestTemplate restTemplate = new RestTemplate(); 
	
	@Autowired
	private RestClient restClient;
	
	private User user;
	
	@BeforeEach
	private void setHeaders(){
		LoginDTO loginDto = new LoginDTO();
		loginDto.setUsername("admin");
		loginDto.setPassword("qwe456");
		
		JsonMapper mapper = new JsonMapper();
		String loginDtoJSON = "";
		try {
			loginDtoJSON = mapper.writeValueAsString(loginDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		restClient.authorization(loginDtoJSON);
		requestEntity = new HttpEntity<String>("", restClient.getHeaders());
		
		
		Role[] roleTab = restClient.getRoles("/roles");
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(roleTab[1]);
		user = new User("Wiktor", "Denis", "aaa", "ccc", "aaa@gmail.com", roleSet);
	}
	
	@Test
	public void getUsersTest() throws Exception {
		ResponseEntity<User[]> responseEntity = 
				restTemplate.exchange("http://localhost:8080/users", 
						HttpMethod.GET, requestEntity, User[].class);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().length != 0);
	}
	
	@Test
	public void saveUserTest() throws Exception {
		String json = getJson(user);
		restClient.post("/users", json);
		assertThat(restClient.getStatus().is2xxSuccessful());
		
		User tempUser = restClient.getUser("/users/username=" + user.getUsername());
		Assertions.assertEquals(user.getUsername(), tempUser.getUsername());
	}
	
	@Test
	public void updateUserTest() throws Exception {
		User tempUser = restClient.getUser("/users/username=" + user.getUsername());
		tempUser.setEmail("wiktor@gmail.com");
		String json = getJson(tempUser);
		restClient.put("/users", json);

		assertThat(restClient.getStatus().is2xxSuccessful());
		Assertions.assertEquals(tempUser.getEmail(), 
				restClient.getUser("/users/username=" + user.getUsername()).getEmail());
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		User tempUser = restClient.getUser("/users/username=" + user.getUsername());
		restClient.delete("/users/" + tempUser.getId());

		assertThat(restClient.getStatus().is2xxSuccessful());
	}
	
	private String getJson(User user) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
	
}
