package com.ecom.customerapi.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class CustomerDto
{
    private Long customerId;
    private String name;
    private String email;
    private String password;
    private String pwdUpdated;
    private String phoneNo;

    private LocalDate dateCreated;

    private LocalDate lastUpdated;
}
