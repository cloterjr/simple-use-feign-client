package com.example.resttemplate.controllers;

import com.example.resttemplate.dto.User;
import com.example.resttemplate.feign.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestTemplateTimeoutController {

    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> show(@PathVariable("id") int id) {

        //        Fake API with Rest Template
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        String fooResourceUrl = "http://localhost:3000/users";

        final String url = String.format("%s/%s", fooResourceUrl, id);

        try{
            User user = restTemplate.getForObject(url, User.class);
            return ResponseEntity.ok(user);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

//        try{
//            //        Fake API with Feign Client
//            final User user = userClient.getUserById(id);
//            return ResponseEntity.ok(user);
//        } catch(Exception ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(430000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(430000);
        return clientHttpRequestFactory;
    }
}
