package com.techelevator.jghomes.services;
import com.techelevator.jghomes.exceptions.HomeServiceException;
import org.openqa.selenium.remote.http.HttpResponse;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
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

        Home[] homes = null;

        //option 1
        homes = restTemplate.getForObject(BASE_SERVICE_URL + "homes", Home[].class);

        //option 2 - makeAuthEntity is provided by niot validated on server because we turned authentication off in rest service
        //homes = restTemplate.exchange(BASE_SERVICE_URL + "homes", HttpMethod.GET, makeAuthEntity(), Home[].class).getBody();

//        ResponseEntity<Home[]> entity = restTemplate.exchange(BASE_SERVICE_URL + "homes", HttpMethod.GET, makeAuthEntity(), Home[].class);
//        homes = entity.getBody();

        return homes;
    }

    public Home retrieveHomesByMLSID(String mlsId) throws HomeServiceException {

        //TODO Needs implemented

        return null;
    }


    public Home addHome(Home home) throws HomeServiceException {

        Home addedHome = null;
        try {
            ResponseEntity<Home> entity = restTemplate.exchange(BASE_SERVICE_URL + "homes", HttpMethod.POST,makeHomeEntity(home), Home.class);
            addedHome = entity.getBody();
            //TODO: normally would return this back out to the app to print a confirmation message
        }
        catch (RestClientResponseException ex) {
            throw new HomeServiceException(ex.getRawStatusCode() + " :" + ex.getResponseBodyAsString());
        }
        catch(ResourceAccessException ex1) {
            throw new HomeServiceException(ex1.getMessage());
        }

        return addedHome;

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
