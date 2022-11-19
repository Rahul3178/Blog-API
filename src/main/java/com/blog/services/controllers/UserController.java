package com.blog.services.controllers;

import com.blog.services.exception.ResourceNotFoundException;
import com.blog.services.payloads.ApiResponse;
import com.blog.services.payloads.UserDTO;
import com.blog.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //Post - create user
   @PostMapping("/addUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        UserDTO createUser= this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }


    // PUT- Update User
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO ,@PathVariable( "userId") Integer uid) throws ResourceNotFoundException {
        UserDTO updatedUser= this.userService.updateUser(userDTO,uid);
        return ResponseEntity.ok(updatedUser);
    }
    // GET fetch all users
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> allUsers=this.userService.getAllUsers();
        return  ResponseEntity.ok(allUsers);
    }
    // Get Single user
    @GetMapping("/singleUser/{userId}")
    public ResponseEntity<UserDTO> getSingleUsers(@PathVariable("userId") Integer uid) throws ResourceNotFoundException {
        UserDTO oneUser=this.userService.getUserById(uid);
        return  ResponseEntity.ok(oneUser);
    }

    // DELETE Delete User

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) throws ResourceNotFoundException {
      this.userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }
}
