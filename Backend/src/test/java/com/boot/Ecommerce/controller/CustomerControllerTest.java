package com.boot.Ecommerce.controller;

import com.boot.Ecommerce.controller.CustomerController;
import com.boot.Ecommerce.entity.Customer;
import com.boot.Ecommerce.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerService = Mockito.mock(CustomerService.class);
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer("FirstName LastName", "first.last@email.com");
        Mockito.when(customerService.saveCustomer(Mockito.any())).thenReturn(customer);
        ResponseEntity<Customer> responseEntity = customerController.saveCustomer(customer);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(customer, responseEntity.getBody());
    }

    @Test
    public void testDeleteCustomer() {
        Mockito.when(customerService.deleteCustomer(Mockito.anyString())).thenReturn("Customer removed successfully");
        ResponseEntity<Void> responseEntity = customerController.deleteCustomer("123");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
