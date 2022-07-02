package pl.piasecki.MyWalletClient;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.piasecki.MyWalletClient.model.Expenditure;
import pl.piasecki.MyWalletClient.model.ExpenditureCategory;
import pl.piasecki.MyWalletClient.model.Role;
import pl.piasecki.MyWalletClient.model.User;

@Component
public class RestClient {

	private final String server = "http://localhost:8080";
	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;

	public RestClient() {
		this.rest = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
	}

	public void authorization(String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = new RestTemplate().exchange(server + "/login", HttpMethod.POST, requestEntity,
				String.class);
		this.setStatus(response.getStatusCode());

		String JwtToken = response.getHeaders().get("Authorization").toString();
		JwtToken = JwtToken.substring(1, JwtToken.length() - 1);
		headers.set("Authorization", JwtToken);

	}

	public <T> List<T> get(String uri, Class<T> klazz) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<List<T>> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<T>>() {
				});
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public Expenditure[] getExpenditures(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<Expenditure[]> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, Expenditure[].class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public User[] getUsers(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<User[]> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, User[].class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public User getUser(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<User> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, User.class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public ExpenditureCategory[] getExpenditureCategory(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<ExpenditureCategory[]> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, ExpenditureCategory[].class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public Role[] getRoles(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<Role[]> responseEntity;
		responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, Role[].class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public String post(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity,
				String.class);
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public void put(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity,
				String.class);
		this.setStatus(responseEntity.getStatusCode());
	}

	public void delete(String uri) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.DELETE, requestEntity,
				String.class);
		this.setStatus(responseEntity.getStatusCode());
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}