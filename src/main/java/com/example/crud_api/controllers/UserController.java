package com.example.crud_api.controllers;

import com.example.crud_api.models.UserModel;
import com.example.crud_api.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getUsers(){
        return userService.getAll();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return userService.save(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable Long id) {
        return userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        boolean ok = userService.delete(id);

        HashMap<String, Object> map = new HashMap<>();

        map.put("success", ok);
        map.put("code", ok ? 200 : 403);

        return map;
    }

}
