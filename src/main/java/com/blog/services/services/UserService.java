package com.blog.services.services;

import com.blog.services.entities.User;
import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.UserDTO;

import java.util.List;

public interface UserService
{
   UserDTO createUser(UserDTO user);
   UserDTO updateUser(UserDTO user, Integer userId) throws ResourceNotFoundException;
   UserDTO getUserById(Integer userId) throws ResourceNotFoundException;
   List<UserDTO> getAllUsers();
   void deleteUser(Integer userId) throws ResourceNotFoundException;

}
