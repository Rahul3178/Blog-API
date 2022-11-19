package com.blog.services.repositories;

import com.blog.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>
{

}
