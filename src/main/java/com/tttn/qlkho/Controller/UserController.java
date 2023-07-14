package com.tttn.qlkho.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.Model.RoleEnum;
import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Service.UserDetailService;
// import com.tttn.qlkho.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private final UserDetailService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    // private final UserDetailsService userDetailsService;

    public UserController(UserDetailService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        // this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        // Tạo đối tượng User với thông tin đăng ký
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        // user.setPassword(password);

        try {
            // Tạo tài khoản người dùng
            userService.createUser(user);
            return ResponseEntity.ok("Tạo tài khoản thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tài khoản đã tồn tại");
        }
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = userService.loadUserByUsername(username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContextHolder.getContext().getAuthentication();
if (authentication != null) {
    for (GrantedAuthority authority : authentication.getAuthorities()) {
        String roleName = authority.getAuthority();
        // Xử lý tên quyền (vai trò) ở đây
        System.out.println("Tên quyền: " + roleName);
    }
}
        return "Đăng nhập thành công!";
    } catch (Exception e) {
        // Xử lý khi xác thực thất bại
        return "Đăng nhập thất bại!";
        }
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
