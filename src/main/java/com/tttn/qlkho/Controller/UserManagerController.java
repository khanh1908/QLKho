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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.DTO.ChangePasswordRequest;
import com.tttn.qlkho.DTO.LoginRequest;
import com.tttn.qlkho.Model.RoleEnum;
import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.UserDetailService;
// import com.tttn.qlkho.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UserManagerController {
    @Autowired
    private final UserDetailService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    // private final UserDetailsService userDetailsService;

    public UserManagerController(UserDetailService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        // this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody LoginRequest registrationRequest) {
    // Lấy thông tin đăng ký từ đối tượng registrationRequest
    String username = registrationRequest.getUsername();
    String password = registrationRequest.getPassword();

    // Tạo đối tượng User với thông tin đăng ký
    User user = new User();
    user.setUserName(username);
    user.setPassword(passwordEncoder.encode(password));

    try {
        // Tạo tài khoản người dùng
        userService.createUser(user);
        return ResponseEntity.ok("Tạo tài khoản thành công");
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Tài khoản đã tồn tại");
    }
}

    @PostMapping("/login")
    public APIResponse login(@RequestBody LoginRequest loginRequest) {
        // Tạo đối tượng User với thông tin đăng ký
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        // user.setPassword(password);

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
            User user = userService.getUserByName(username);
            APIResponse response = new APIResponse(true, user, "Đăng nhập thành công");
            return response;
        } catch (Exception e) {
            // Xử lý khi xác thực thất bại
            APIResponse response = new APIResponse(false, null, "Đăng nhập thất bại");
            return response;
        }
    }
    @PostMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully.");
    }
    @PostMapping("/forgot-password")
    public APIResponse forgotPassword(@RequestParam("email") String email) {
        try {
            userService.sendPassToEmail(email);
            APIResponse response = new APIResponse(true, null, "Mật khẩu đã được gửi đến email. Vui lòng kiểm tra lại!");
            return response;
        } catch (IllegalArgumentException e) {
            APIResponse response = new APIResponse(false, e, "Vui lòng kiểm tra lại Email hoặc tài khoản của bạn!");
            return response;
        } catch (Exception e) {
            APIResponse response = new APIResponse(false, e, "Vui lòng kiểm tra lại Email hoặc tài khoản của bạn!");
            return response;
        }
    }
    @GetMapping("/getall")
    public APIResponse getAllUsers() {
        List<User> Userlist = userService.getAllUsers();
        APIResponse response = new APIResponse(true, Userlist, "danh sach User đã được lấy thành công");
        return response;
    }
}
