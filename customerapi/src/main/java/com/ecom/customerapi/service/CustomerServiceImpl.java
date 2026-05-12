package com.ecom.customerapi.service;

import com.ecom.customerapi.dto.CustomerDto;
import com.ecom.customerapi.entity.CustomerEntity;
import com.ecom.customerapi.exception.ResourceNotFoundException;
import com.ecom.customerapi.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private CustomerRepository customerrepo;

    @Override
    public CustomerDto addNewCustomerserv(CustomerDto customerdto)
    {
        /*
            1.check if the customer is present previously or not using customer name
            2.if not there create a new account
         */

        log.info("control entered addNewCustomerserv = "+customerdto);

        CustomerEntity customerentity = modelmapper.map(customerdto , CustomerEntity.class);
        CustomerEntity savedcustomer = customerrepo.save(customerentity);

        log.info("user with this name is saved in customer-service = "+customerdto.getName());
        return modelmapper.map(savedcustomer , CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers()
    {
        List<CustomerEntity> allcutomerentities = customerrepo.findAll();

        List<CustomerDto> allcutomerdtos = allcutomerentities.stream()
                .map(individualcustomerentiy -> modelmapper.map(individualcustomerentiy , CustomerDto.class))
                .toList();

        return allcutomerdtos;
    }

    @Override
    public CustomerDto getCustomerByIdserv(Long customerId)
    {
        /*
            1.check if the customer is present with the id or not
         */

        CustomerEntity customerentity = customerrepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("customer","customerId",""+customerId));

        return modelmapper.map(customerentity , CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerServ(Long customerId,CustomerDto customerdto)
    {
        CustomerEntity customerentity = customerrepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("customer","customerId",""+customerdto.getCustomerId()));

        customerentity.setName(customerdto.getName());
        customerentity.setEmail(customerdto.getEmail());
        customerentity.setPassword(customerdto.getPassword());
        customerentity.setPhoneNo(customerdto.getPhoneNo());
        customerentity.setLastUpdated(customerdto.getLastUpdated());
        customerentity.setPwdUpdated(customerdto.getPwdUpdated());

        CustomerEntity updatedcustomer = customerrepo.save(customerentity);

        return modelmapper.map(updatedcustomer , CustomerDto.class);
    }

    @Override
    public String deleteCustomerServ(Long customerId)
    {
        CustomerEntity customerentity = customerrepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("customer","customerId",""+customerId));

        customerrepo.deleteById(customerId);
        return "success";
    }


}
