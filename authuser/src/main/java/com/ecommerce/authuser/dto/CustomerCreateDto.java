package com.ecommerce.authuser.dto;


import lombok.Data;

@Data
public class CustomerCreateDto
{
    private Long customerId;
    private String name;
    private String email;
    private String phoneNo;

}
