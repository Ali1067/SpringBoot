package com.ali.recipe_sharing_youtube.Repository;

import com.ali.recipe_sharing_youtube.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //no duplication email allowed
    public User findByEmail(String email);
}
