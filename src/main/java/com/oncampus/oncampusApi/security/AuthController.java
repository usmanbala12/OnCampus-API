package com.oncampus.oncampusApi.security;

import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Result getLoginInfo(Authentication authentication) {
        return new Result(true, StatusCode.SUCCESS, "User Info and Json Web Token", authService.createLoginInfo(authentication));
    }

    @PostMapping("/signup")
    public Result signUpUser(@RequestBody User user) {
        System.out.println(user.toString());
        UserDto userDto = authService.signup(user);
        return new Result(true, StatusCode.SUCCESS, "User Created", userDto);
    }
}
