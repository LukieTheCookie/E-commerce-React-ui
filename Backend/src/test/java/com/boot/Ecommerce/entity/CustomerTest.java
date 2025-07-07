package com.boot.Ecommerce.entity;

import com.boot.Ecommerce.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("FirstName LastName", "first.last@email.com");
        Assertions.assertNull(customer.getId());
        Assertions.assertEquals("FirstName LastName", customer.getName());
        Assertions.assertEquals("first.last@email.com", customer.getEmail());
    }
}
