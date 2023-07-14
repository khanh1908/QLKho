package com.tttn.qlkho.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Ropository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    public User createUser(User user) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (findByUsername(user.getUserName()) != null) {
            throw new IllegalArgumentException("Người dùng đã tồn tại");
        }

        // Thực hiện lưu người dùng vào cơ sở dữ liệu
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
         List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Vai trò user mặc định
        
        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // Vai trò admin
        }
        
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            authorities
        );
}
}