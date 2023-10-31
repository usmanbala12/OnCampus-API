package com.oncampus.oncampusApi.security;

import com.oncampus.oncampusApi.user.MyUserPrincipal;
import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.UserService;
import com.oncampus.oncampusApi.user.converter.UserToUserDtoConverter;
import com.oncampus.oncampusApi.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserService userService;



    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // create user info
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        User user = principal.getUser();
        UserDto convert = userToUserDtoConverter.convert(user);
        // create jwt
        String token = jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfo", convert);
        loginResultMap.put("token", token);

        return loginResultMap;
    }

    public UserDto signup(User user) {
        User created = userService.save(user);
        return userToUserDtoConverter.convert(created);
    }
}

