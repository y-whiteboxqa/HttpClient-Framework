package com.qa.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

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
        apiUrl = prop.getProperty("serviceURLget");

        // append both url's
        url = baseUrl + apiUrl;

    }

    @Test(priority = 1)
    public void getAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        response = restClient.get(url);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code: " + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        // to get response as string
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

        // to convert string response to Json object
        JSONObject responseJson = new JSONObject(responseBody);
        System.out.println("Json Response from API: " + responseJson);

        // single value assertions
        // per_page value assertion
        String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
        System.out.println("value of per page is: " + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);

        // total value assertion
        String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("value of total is: " + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        // get the value from json array
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(lastName);

        // get all headers returns header array
        Header[] headerArray = response.getAllHeaders();

        // create HashMap object,because stores values in key value pairs and header
        // info is key value pairs
        HashMap<String, String> allHeaders = new HashMap<String, String>();

        // iterate hashmap and print all headers
        for (Header header : headerArray) {
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("Headers: " + allHeaders);

    }

    // test for get request with headers
    @Test(priority = 2)
    public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
        restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        response = restClient.get(url, headerMap);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code: " + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        // to get response as string
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

        // to convert string response to Json object
        JSONObject responseJson = new JSONObject(responseBody);
        System.out.println("Json Response from API: " + responseJson);

        // single value assertions
        // per_page value assertion
        String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
        System.out.println("value of per page is: " + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);

        // total value assertion
        String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("value of total is: " + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        // get the value from json array
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(lastName);

        // get all headers returns header array
        Header[] headerArray = response.getAllHeaders();

        // create HashMap object,because stores values in key value pairs and header
        // info is key value pairs
        HashMap<String, String> allHeaders = new HashMap<String, String>();

        // iterate hashmap and print all headers
        for (Header header : headerArray) {
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("Headers: " + allHeaders);

    }
}
