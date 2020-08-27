package com.example.resttemplate.feign.client;

import com.example.resttemplate.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="users-api", url="http://localhost:3000")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUserById(@PathVariable("id") int id);
}
