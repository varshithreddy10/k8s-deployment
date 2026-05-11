package com.ecommerce.authuser.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto
{
    private Long customerId;
    private String name;
    private String email;
    private String phoneNo;

    private String password;

}
