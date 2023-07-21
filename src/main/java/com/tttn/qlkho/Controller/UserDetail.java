package com.tttn.qlkho.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.Model.User;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.UserDetailService;
@RestController
@RequestMapping("/User")
@PreAuthorize("hasRole('USER')")
public class UserDetail {
    @Autowired
    UserDetailService UserService;

    @GetMapping("/{id}")
    public APIResponse getUserByID(@PathVariable long id) {
        User user = UserService.getUserById(id);
        APIResponse response = new APIResponse(true, user, "lay thong tin user");
        return response;
    }
    @PutMapping("/sua/{id}")
    public APIResponse SuaUser(@PathVariable long id, @RequestBody User user) {
        try{
        User UpdateUser = UserService.getUserById(id);
            if(UpdateUser==null) {
                APIResponse response = new APIResponse(false, null, "User khong co");
                return response;
            }else {
                if (user.getAddress() != null) {
                    UpdateUser.setAddress(user.getAddress());
                }
                if (user.getCCCD() != null) {
                    UpdateUser.setCCCD(user.getCCCD());
                }
                if (user.getEmail() != null) {
                    UpdateUser.setEmail(user.getEmail());
                }
                if (user.getHo() != null) {
                    UpdateUser.setHo(user.getHo());
                }
                if (user.getTen() != null) {
                    UpdateUser.setTen(user.getTen());
                }
                if (user.getSDT() != null) {
                    UpdateUser.setSDT(user.getSDT());
                }
                UserService.updateUser(id, UpdateUser);
                APIResponse response = new APIResponse(true, UpdateUser, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
