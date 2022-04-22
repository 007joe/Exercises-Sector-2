package com.techelevator.jghomes.services;
import com.techelevator.jghomes.exceptions.HomeServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.jghomes.models.Home;

public class HomeService {

    private final String BASE_SERVICE_URL;
    public String AUTH_TOKEN = "";
    private RestTemplate restTemplate = new RestTemplate();

    public HomeService(String baseUrl) {
        this.BASE_SERVICE_URL = baseUrl;
    }

    public Home[] retrieveListOfHomes() throws HomeServiceException {

        //TODO Needs implemented

        return null;
    }

    public Home retrieveHomesByMLSID(String mlsId) throws HomeServiceException {

        //TODO Needs implemented

        return null;
    }


    public void addHome(Home home) throws HomeServiceException {

        //TODO Needs implemented

    }

    public void deleteHome(String mlsId) throws HomeServiceException {

       //TODO Needs implemented

    }

    /**
     * IMPORTANT!!!  This gets set by the client once authentication occurs
     *
     * @param aUTH_TOKEN
     */
    public void setAUTH_TOKEN(String aUTH_TOKEN) {
        AUTH_TOKEN = aUTH_TOKEN;
    }


    // MAKE A HOME ENTITY TO USE IN THE BODY
    private HttpEntity<Home> makeHomeEntity(Home home) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Home> entity = new HttpEntity<>(home, headers);
        return entity;
    }

    // MAKE AN AUTH ENTITY ONLY
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

}
