// package com.tttn.qlkho.Service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import com.tttn.qlkho.Model.User;
// import com.tttn.qlkho.Ropository.UserRepository;

// @Service
// public class UserService extends UserDetailService{
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;

//     public Boolean checkUserExist(String userName){
//         for (User user: getAllUsers()
//              ) {
//             if(userName.equals(user.getUserName()))
//                 return true;
//         }
//         return  false;
        
//     }

//     public User registerUser(User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }
//     public List<User> getAllUsers() {
//         return (List<User>) userRepository.findAll();
//     }
// }
