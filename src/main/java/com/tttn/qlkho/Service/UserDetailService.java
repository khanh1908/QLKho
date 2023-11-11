package com.tttn.qlkho.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.RoleEnum;
import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    public User getUserByCCCD(String cccd) {
        return userRepository.findByCCCD(cccd);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserBySDT(String sdt) {
        return userRepository.findBySDT(sdt);
    }
    public User createUser(User user) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (findByUsername(user.getUserName()) != null ) {
            throw new IllegalArgumentException("Người dùng đã tồn tại");
        }
        else{
            if(user.getRole()==null)
            {
                user.setRole(RoleEnum.USER);
            }
        }
        // Thực hiện lưu người dùng vào cơ sở dữ liệu
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByName(String Username) {
        return userRepository.findByUserName(Username);
    }
    public User updateUser(Long id, User user) {
        return userRepository.save(user);
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
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            // Mật khẩu cũ khớp, tiến hành thay đổi mật khẩu
            // String newEncodedPassword = passwordEncoder.encode(newPassword);
            // user.setPassword(passwordEncoder.encode(password));
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Incorrect old password");
        }
    }
    public void sendPassToEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " not found.");
        }
        String pass = generatePass();
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
        sendPassEmail(email, pass);
        // System.out.println("abbbbbbbbbbbbbbbbbb"+ pass);
    }

    private String generatePass() {
        int PassLength = 6;
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder(PassLength);

        for (int i = 0; i < PassLength; i++) {
            password.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return password.toString();
    }

    private void sendPassEmail(String userEmail, String pass) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Password Reset");
        message.setText("Your Pass: " + pass);
        javaMailSender.send(message);
    }
}