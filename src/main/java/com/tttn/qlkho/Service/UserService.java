package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Ropository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // public List<User> getAllEmployee() {
    //     return  userRepository.findAllEmployee();
    // }

    // public List<User> SearchUser( String email, String name,String sdt) {
    //     return  userRepository.searchUser(  email,  name, sdt);
    // }


    // public Boolean checkUserExist(String userName){
    //     for (User user: getAllUsers()
    //          ) {
    //         if(userName.equals(user.getUserName()))
    //             return true;
    //     }
    //     return  false;
        
    // }

    // public User getUserById(Long id) {
    //     return userRepository.findById(id).orElse(null);
    // }
}
