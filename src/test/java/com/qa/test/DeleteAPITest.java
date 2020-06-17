package com.qa.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class DeleteAPITest extends TestBase{

    
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
        apiUrl = prop.getProperty("serviceURLdelete");

        // append both url's
        url = baseUrl + apiUrl;

    }
    
    
    
    
    @Test
    public void deleteAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        response = restClient.delete(url);
        
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code: " + statusCode);
        
        String statusLine = response.getStatusLine().toString();
        System.out.println("StatusLine is: " + statusLine);
        
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204, "Status code is not 204");
        Assert.assertEquals(statusLine,"HTTP/1.1 204 No Content", "StatusLine did not match");

    }
    
    
}
