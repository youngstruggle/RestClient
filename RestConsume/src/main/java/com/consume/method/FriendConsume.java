package com.consume.method;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.consume.model.Friend;

public class FriendConsume {

	public static final String REST_ENDPOINT_URI = "http://localhost:8080/restserviceproject/";

	/* GET FRIENDS LIST */
	@SuppressWarnings("unchecked")
	public void listAllFriends() {
		System.out
				.println("----------- Testing listAllCustomers API -----------");
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate
				.getForObject(REST_ENDPOINT_URI + "/friends/", List.class);

		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("firstName =" + map.get("firstName")
						+ ", lastName=" + map.get("lastName"));
			}

			String response = restTemplate.getForObject(REST_ENDPOINT_URI
					+ "/friends/", String.class);
			System.out.println("Response " + response);
		} else {
			System.out.println("---------- No user exist ----------");
			System.out.println("---------- Transaction Finish ----------");
		}
	}

	/* GET FRIEND BY ID */
	public void getFriends(String id) {
		System.out.println("---------- Testing getFriend API ----------");
		RestTemplate restTemplate = new RestTemplate();
		Friend friends = restTemplate.getForObject(REST_ENDPOINT_URI
				+ "/friends/" + id, Friend.class);

		if (friends != null) {
			ResponseEntity<String> entity = restTemplate.getForEntity(
					REST_ENDPOINT_URI + "/friends/" + id, String.class);
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

	/* POST FRIEND */
	public void createFriend(Friend friend) {
		System.out.println("---------- Testing create Friend API ----------");
		RestTemplate restTemplate = new RestTemplate();
		Friend friends = new Friend(friend.getFirstName(),
				friend.getLastName());

		ResponseEntity<Friend> entity = restTemplate.postForEntity(
				REST_ENDPOINT_URI + "/friends/", friends, Friend.class);

		Friend body = entity.getBody();
		MediaType contentType = entity.getHeaders().getContentType();
		HttpStatus statusCode = entity.getStatusCode();

		System.out.println();
		System.out.println("---------- Response Create FRIEND ----------");
		System.out.println("Body == " + body);
		System.out.println("Content == type " + contentType);
		System.out.println("Status code == " + statusCode);
	}

	/* DELETE FRIEND */
	public void deleteFriend(String id) {
		System.out.println("Testing delete FRIEND API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_ENDPOINT_URI + "/friends/" + id);
	}

	/* PUT FRIEND */
	public void updateFriend(Friend friend, String id) {
		System.out.println("Testing update FRIEND API----------");
		RestTemplate restTemplate = new RestTemplate();
		Friend friends = new Friend(friend.getId(), friend.getFirstName(),
				friend.getLastName());
		restTemplate.put(REST_ENDPOINT_URI + "/friends/" + id, friends);
	}
}
