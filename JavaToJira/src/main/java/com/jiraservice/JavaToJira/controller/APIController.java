package com.jiraservice.JavaToJira.controller;

import javax.naming.AuthenticationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiraservice.JavaToJira.payload.IssueBody;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

@RestController
@RequestMapping("/jira")
public class APIController {

	@PostMapping
	public ResponseEntity<IssueBody> createUser(@RequestBody IssueBody user) {
		try {
			String BASE_URL = "https://optimizer007.atlassian.net";
			String auth = new String(Base64.encode(
					"sudeept.2409@gmail.com:ATATT3xFfGF0wEznBYuZhsxFctZ4et-4d3WMlIYwNWTw1Zd4kyTwpzKdGS74zJNgNSlhM7mMmF5NxZsAQGHlav-0IsjdbcEP7aaszLbSWnxgb3h9EWcKZNQaRkKzF7yTDacw7KauD3GzdvK4zVzHJyqijLDE-_mLsoXuF37XZMNX4S8qcvqtLRI=B1A157DB"));
			String createIssueData = "{\"fields\":{\"project\":{\"key\":\""+user.getKey()+"\"},\"summary\":\""+user.getSummary()+"\",\"description\":\""+user.getDescription()+"\",\"issuetype\":{\"name\":\""+user.getIssueTypeName()+"\"}}}";
			String issue = invokePostMethod(auth, BASE_URL + "/rest/api/2/issue", createIssueData);
			System.out.println(issue);
			JSONObject issueObj = new JSONObject(issue);
			String newKey = issueObj.getString("key");
			System.out.println("Key:" + newKey);
		} catch (AuthenticationException e) {
			System.out.println("Username or Password wrong!");
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			System.out.println("Error invoking REST method");
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println("Invalid JSON output");
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	String invokePostMethod(String auth, String url, String data)
			throws AuthenticationException, ClientHandlerException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
				.accept("application/json").post(ClientResponse.class, data);
		int statusCode = response.getStatus();
		if (statusCode == 401) {
			throw new AuthenticationException("Invalid Username or Password");
		}
		return response.getEntity(String.class);
	}

}
