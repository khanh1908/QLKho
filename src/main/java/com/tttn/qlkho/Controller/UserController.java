package com.tttn.qlkho.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    //  @GetMapping()
    // public ResponseEntity<?> getAllUser() {
    //     List<User> user =  userService.getAllUsers();
    //     // int totalproduct = totalquantity.calculateTotalPrice(products);
    //     return ResponseEntity.ok(user);
    // }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
