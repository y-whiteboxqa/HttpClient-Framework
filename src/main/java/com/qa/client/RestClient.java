package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

    // create GET method without headers
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

        // this will create a simple HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get request
        HttpGet httpget = new HttpGet(url);

        // this hits the GET url (sending the request)
        CloseableHttpResponse response = httpClient.execute(httpget);

        return response;

    }

    // GET method with headers
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
            throws ClientProtocolException, IOException {

        // this will create a simple HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get request
        HttpGet httpget = new HttpGet(url);

        // adding header to get request
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }

        // this hits the GET url (sending the request)
        CloseableHttpResponse response = httpClient.execute(httpget);

        return response;

    }

    // POST method
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
            throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        // for payload
        httppost.setEntity(new StringEntity(entityString));

        // for headers
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse response = httpClient.execute(httppost);

        return response;

    }

    
    
    
 // PUT method
    public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap)
            throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);

        // for payload
        httpput.setEntity(new StringEntity(entityString));

        // for headers
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpput.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse response = httpClient.execute(httpput);

        return response;

    }

    
    
    //DELETE method
    public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException{

        // this will create a simple HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get request
        HttpDelete httpdelete = new HttpDelete(url);

        // this hits the GET url (sending the request)
        CloseableHttpResponse response = httpClient.execute(httpdelete);

        return response;

    }
}
