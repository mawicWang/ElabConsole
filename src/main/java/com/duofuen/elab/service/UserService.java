package com.duofuen.elab.service;

import com.alibaba.fastjson.JSONObject;
import com.duofuen.elab.domain.Users;
import com.duofuen.elab.domain.UsersRepository;
import com.duofuen.elab.dto.ChangePasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UsersRepository usersRepository, BCryptPasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    public JSONObject changePassword(ChangePasswordDto changePasswordDto) {
        JSONObject json = new JSONObject();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<Users> usersOptional = usersRepository.findById(username);
        Assert.isTrue(usersOptional.isPresent(), "WTF current login user is not exists");

        if (!encoder.matches(changePasswordDto.getCurPassword(), usersOptional.get().getPassword())) {
            json.put("success", false);
            json.put("msg", "当前密码输入错误！");
            return json;
        }

        String passwordEncoded = encoder.encode(changePasswordDto.getPassword());

        // 保存密码
        Users user = usersOptional.get();
        user.setPassword(passwordEncoded);
        usersRepository.save(user);
        json.put("success", true);
        json.put("msg", "密码修改成功");
        return json;
    }

}
