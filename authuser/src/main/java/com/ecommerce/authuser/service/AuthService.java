package com.ecommerce.authuser.service;


import com.ecommerce.authuser.Exception.CustomerIdNotMatchException;
import com.ecommerce.authuser.Exception.ResourceNotFoundException;
import com.ecommerce.authuser.dto.*;
import com.ecommerce.authuser.entity.UserEntity;

import com.ecommerce.authuser.feignclients.Customer_Api_Feignclient;
import com.ecommerce.authuser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService
{
    // 1 time
    @Autowired
    private UserRepository userrepo;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private Customer_Api_Feignclient customerfeign;


    public Boolean creatingCustomerInCustomerService(UserEntity savedUser)
    {
        boolean result;
        log.info("control entered the creatingCustomerInCustomerService with userentity = "+savedUser);

        CustomerCreateDto customercreatedto = modelmapper.map(savedUser , CustomerCreateDto.class);
        log.info("converting the UserEntity to CustomerCreateDto = "+customercreatedto);

        try
        {
            CustomerCreateDto createdcustomer = customerfeign.createCustomer(customercreatedto);
            log.info("saved customer in the customer-service is = "+createdcustomer);

            if(!savedUser.getCustomerId().equals(createdcustomer.getCustomerId()))
            {
                throw new CustomerIdNotMatchException("customerId's in the auth-service = "+savedUser.getCustomerId()+" and the Customer-service = "+createdcustomer.getCustomerId()+" doesnot match");
            }
            else
            {
                log.info("both the ids are matched savedUser.getCustomerId() = "+savedUser.getCustomerId()+" == createdcustomer.getCustomerId() = "+createdcustomer.getCustomerId());
            }

            result=true;
        }
        catch(Exception e)
        {

            result = false;
            log.error("exception occured while creating customer in customer-service "+e.getMessage());
            userrepo.deleteById(savedUser.getCustomerId());
            log.info("creation of the user is failed in customer-service so we are deleting the user-entity from the database ");
        }

        return result;

    }

    public UserDto signUp(SignUpDto signUpDto)
    {
        log.info("control entered the signup() method with signupdto as "+signUpDto);
        Optional<UserEntity> user = userrepo.findByEmail(signUpDto.getEmail());
        if(user.isPresent())
        {
            throw new ResourceNotFoundException("User with email already exits "+ signUpDto.getEmail());
        }

        UserEntity toBeCreatedUser = modelmapper.map(signUpDto, UserEntity.class);

        log.info("before saving userentity = "+toBeCreatedUser);

        UserEntity savedUser = userrepo.save(toBeCreatedUser);

        log.info("execution of the signup is completed and the saved user is "+savedUser);

        if(creatingCustomerInCustomerService(savedUser)==false)
        {
            throw new RuntimeException("Failed to create customer in customer-service");
        }

        return modelmapper.map(savedUser, UserDto.class);
    }

    public LoginResponseDto login(LoginDto dto) {

        UserEntity user = userrepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!dto.getPassword().equals(user.getPassword()))
        {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponseDto(user.getCustomerId(), "login-successful");
    }


}
