package com.ali.recipe_sharing_youtube.service;

import com.ali.recipe_sharing_youtube.Models.User;

public interface UserService {

    public User findUserById(Long Id) throws Exception;
    public User findUserByJwt(String jwt) throws Exception;
}
