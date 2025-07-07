package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Customer;
import com.boot.Ecommerce.repository.CustomerRepository;
import com.boot.Ecommerce.service.CustomerService;
import com.boot.Ecommerce.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceImplTest {
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("John Doe", "johndoe@example.com");
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
        Customer savedCustomer = customerService.saveCustomer(customer);

        Assertions.assertEquals(customer.getName(), savedCustomer.getName());
        Assertions.assertEquals(customer.getEmail(), savedCustomer.getEmail());
    }

    @Test
    public void testDeleteCustomer() {
        Mockito.doNothing().when(customerRepository).deleteById(Mockito.anyString()); // Delete a customer
        String result = customerService.deleteCustomer("123");

        Assertions.assertEquals("Customer removed successfully", result);
    }
}
