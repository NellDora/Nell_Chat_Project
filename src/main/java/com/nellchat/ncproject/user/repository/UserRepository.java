package com.nellchat.ncproject.user.repository;

import com.nellchat.ncproject.user.domain.User;

public interface UserRepository {

    public void save(User user);

    public User findByNumber(Long num);

    public User findById(String id);

    public void updatePassword(Long number ,String password);
}
