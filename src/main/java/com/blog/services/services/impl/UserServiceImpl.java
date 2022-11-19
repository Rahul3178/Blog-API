package com.blog.services.services.impl;

import com.blog.services.entities.User;
import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.UserDTO;
import com.blog.services.repositories.UserRepo;
import com.blog.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserRepo userRepo;

    @Override
    public UserDTO createUser(UserDTO userDto) {

        /*here we have to convert user dto to user to save in database*/
        User us=dtoToUser(userDto);
       User savedUser=this.userRepo.save(us);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) throws ResourceNotFoundException {
        User fuser=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user_Id",userId));
        fuser.setName(userDto.getName());
        fuser.setAbout(userDto.getAbout());
        fuser.setEmail(userDto.getEmail());
        fuser.setPassword(userDto.getPassword());

        User updatedUser=this.userRepo.save(fuser);
        UserDTO userDto1= userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDTO getUserById(Integer userId) throws ResourceNotFoundException {
        User fuser=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user_Id",userId));
        return this.userToDto(fuser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
      List<UserDTO> dtousers= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return dtousers;
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundException {
       User deleteUser=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(deleteUser);
    }

    /*Here we write 2 methods to convert user to user dto and vice versa*/

    private User dtoToUser(UserDTO userDto)
    {
        User user= new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
    }
    /* it takes user object then convert it into UserDto object
    * we can opt for this by using model wrapper libraries
    * */
    private UserDTO userToDto(User user)
    {
        UserDTO userDto= new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
