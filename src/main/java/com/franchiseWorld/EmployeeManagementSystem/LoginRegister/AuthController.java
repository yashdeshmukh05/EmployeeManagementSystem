package com.franchiseWorld.EmployeeManagementSystem.LoginRegister;

import com.franchiseWorld.EmployeeManagementSystem.Config.JwtTokenProvider;
import com.franchiseWorld.EmployeeManagementSystem.Entity.*;
import com.franchiseWorld.EmployeeManagementSystem.Exception.UserException;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;
import com.franchiseWorld.EmployeeManagementSystem.Response.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;



    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;


    }

    @PostMapping("/signup/Employee")
    public ResponseEntity<AuthResponse> createEmployeeHandler(@Valid @RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        UserRole userRole = UserRole.ROLE_EMPLOYEE;
        String mobile = user.getMobile();
        List<Address> address = user.getAddresses();
        Position position = user.getPosition();
        Department department = user.getDepartment();


        User isEmailExist = userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist != null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }

        // Create new user
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(UserRole.ROLE_EMPLOYEE);
        createdUser.setMobile(mobile);
        createdUser.setAddresses(address);
        createdUser.setPosition(position);
        createdUser.setDepartment(department);


        User savedUser = userRepository.save(createdUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }

    @PostMapping("/signup/HR")
    public ResponseEntity<AuthResponse> createHRHandler(@Valid @RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        UserRole userRole = UserRole.ROLE_HR;
        String mobile = user.getMobile();
        List<Address> address = user.getAddresses();
        Position position = user.getPosition();
        Department department = user.getDepartment();

        User isEmailExist=userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist!=null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }

        // Create new user
        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(UserRole.ROLE_HR);
        createdUser.setMobile(mobile);
        createdUser.setAddresses(address);
        createdUser.setPosition(position);
        createdUser.setDepartment(department);



        User savedUser= userRepository.save(createdUser);



        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }

}

