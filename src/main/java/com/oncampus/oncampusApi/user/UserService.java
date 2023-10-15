package com.oncampus.oncampusApi.user;

import com.oncampus.oncampusApi.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public User save(User user) {
       return userRepository.save(user);
    }

    public User update(Integer userId, User update) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(update.getFirstName());
                    user.setLastName(update.getLastName());
                    user.setRole(update.getRole());

                    return userRepository.save(user);
                }).orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public void delete(Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("user", userId));
        userRepository.deleteById(userId);
    }
}


