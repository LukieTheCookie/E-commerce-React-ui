package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Customer;
import com.boot.Ecommerce.repository.CustomerRepository;
import com.boot.Ecommerce.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public String deleteCustomer(String id) {
        customerRepository.deleteById(id);
        return "Customer removed successfully";
    }

    @Override
    public boolean customerExists(String name, String email) {
        return customerRepository.existsByNameAndEmail(name, email);
    }
}
