package com.consume.method;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.consume.model.Customer;

public class CustomerConsume {

	public static final String REST_ENDPOINT_URI = "http://localhost:8080/restserviceproject/";

	/* GET CUSTOMER LIST */
	@SuppressWarnings("unchecked")
	public void listAllCustomers() {
		System.out
				.println("----------- Testing listAllCustomers API -----------");
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate
				.getForObject(REST_ENDPOINT_URI + "/customers/", List.class);

		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("Customer : id=" + map.get("id")
						+ ", firstName =" + map.get("firstName")
						+ ", lastName=" + map.get("lastName") + ", email="
						+ map.get("email") + ", mobile=" + map.get("mobile"));
			}

			String response = restTemplate.getForObject(REST_ENDPOINT_URI
					+ "/customers/", String.class);
			System.out.println("Response " + response);
		} else {
			System.out.println("---------- No user exist ----------");
			System.out.println("---------- Transaction Finish ----------");
		}
	}

	/* GET CUSTOMER BY ID */
	public void getCustomer(Integer id) {
		System.out.println("---------- Testing getCustomers API ----------");
		RestTemplate restTemplate = new RestTemplate();
		Customer customer = restTemplate.getForObject(REST_ENDPOINT_URI
				+ "/customers/" + id, Customer.class);

		if (customer != null) {
			ResponseEntity<String> entity = restTemplate.getForEntity(
					REST_ENDPOINT_URI + "/customers/" + id, String.class);
			String body = entity.getBody();
			MediaType contentType = entity.getHeaders().getContentType();
			HttpStatus statusCode = entity.getStatusCode();

			System.out.println();
			System.out.println("---------- Response Customer By Id ----------");
			System.out.println("Body == " + body);
			System.out.println("Content == type " + contentType);
			System.out.println("Status code == " + statusCode);
		}
	}

	/* POST CUSTOMER */
	public void createCustomers(Customer cust) {
		System.out.println("---------- Testing create Customer API ----------");
		RestTemplate restTemplate = new RestTemplate();
		Customer customer = new Customer(cust.getId(), cust.getFirstName(),
				cust.getLastName(), cust.getEmail(), cust.getMobile());

		// Customer resp =
		// restTemplate.postForObject(REST_ENDPOINT_URI+"/customers/", customer,
		// Customer.class);
		ResponseEntity<Customer> entity = restTemplate.postForEntity(
				REST_ENDPOINT_URI + "/customers/", customer, Customer.class);

		Customer body = entity.getBody();
		MediaType contentType = entity.getHeaders().getContentType();
		HttpStatus statusCode = entity.getStatusCode();

		System.out.println();
		System.out.println("---------- Response Create Customer ----------");
		System.out.println("Body == " + body);
		System.out.println("Content == type " + contentType);
		System.out.println("Status code == " + statusCode);
	}

	/* DELETE CUSTOMER */
	public void deleteCustomer(Integer id) {
		System.out.println("Testing delete User API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_ENDPOINT_URI + "/customers/" + id);
	}

	/* PUT CUSTOMER */
	public void updateUser(Customer cust, Integer id) {
		System.out.println("Testing update User API----------");
		RestTemplate restTemplate = new RestTemplate();
		Customer customer = new Customer(cust.getId(), cust.getFirstName(),
				cust.getLastName(), cust.getEmail(), cust.getMobile());
		restTemplate.put(REST_ENDPOINT_URI + "/customers/" + id, customer);
	}
}
