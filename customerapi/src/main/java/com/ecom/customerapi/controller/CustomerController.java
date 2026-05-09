package com.ecom.customerapi.controller;

import com.ecom.customerapi.dto.CustomerDto;
import com.ecom.customerapi.repository.CustomerRepository;
import com.ecom.customerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{

    @Autowired
    private CustomerService customerservice;

    @Autowired
    private CustomerRepository customerrepo;

    @PostMapping("/add/customer")
    public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerdto)
    {
        CustomerDto customersaved = customerservice.addNewCustomerserv(customerdto);
        return new ResponseEntity<>(customersaved , HttpStatus.CREATED);
    }

    @GetMapping("/getall/customers")
    public ResponseEntity<List<CustomerDto>> getallCustomers()
    {
        List<CustomerDto> allcustomers = customerservice.getAllCustomers();
        return new ResponseEntity<>(allcustomers , HttpStatus.CREATED);
    }

    @GetMapping("/get/customer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId)
    {
        CustomerDto onecustomer = customerservice.getCustomerByIdserv(customerId);
        return new ResponseEntity<>(onecustomer , HttpStatus.CREATED);
    }

    @PutMapping("/get/category/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDto customerdto)
    {
        CustomerDto onecustomer = customerservice.updateCustomerServ(customerId ,customerdto);
        return new ResponseEntity<>(onecustomer , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/category/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId)
    {
        String message = customerservice.deleteCustomerServ(customerId);
        return new ResponseEntity<>(message , HttpStatus.CREATED);
    }






}
