package com.qa.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

    TestBase testBase;
    String baseUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse response;

   
   
    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        baseUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURLpost");

        // append both url's
        url = baseUrl + apiUrl;

    }

    @Test
    public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {

        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        // jackson API to convert data(java object) into json object..this is called
        // marshalling
        ObjectMapper mapper = new ObjectMapper();

        // create Users class variable and pass parameters to the constructor
        Users users = new Users("morpheus", "leader"); // expected users object

        // object to json convertion
        mapper.writeValue(new File("/Users/yamuna/eclipse-workspace/HTTPClientFramework/src/main/java/com/qa/data/users.json"),users);

        // convert json object to json string and store it in string variable
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        // attach it to post method
        response = restClient.post(url, usersJsonString, headerMap);

        // verify status code
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

        // validate json string
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API is: " + responseJson);

        // validate response
        // convert json object to java object (unmarshalling)
        Users usersResObj = mapper.readValue(responseString, Users.class); // actual users object
        System.out.println(usersResObj);

        // compare actual vs expected
        Assert.assertTrue(users.getName().equals(usersResObj.getName()));

        Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));

        // printing id and created at values from response
        System.out.println(usersResObj.getId());
        System.out.println(usersResObj.getCreatedAt());
    }

}
