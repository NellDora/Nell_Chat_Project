package com.nellchat.ncproject.user.service;

import com.nellchat.ncproject.user.domain.User;
import com.nellchat.ncproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public void updateInfo(User user){
        User findUser = userRepository.findByNumber(user.getNumber());
        findUser.updateInfo(user.getUserName(), user.getUserNickname(), user.getEmail());

    }

    public User findByNumber(Long num){
        return userRepository.findByNumber(num);

    }

    public User findById(String Id){
        return  userRepository.findById(Id);
    }


}
