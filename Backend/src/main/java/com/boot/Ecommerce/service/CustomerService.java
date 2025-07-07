package com.boot.Ecommerce.service;
import com.boot.Ecommerce.entity.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    String deleteCustomer(String id);
    boolean customerExists(String name, String email);
}
