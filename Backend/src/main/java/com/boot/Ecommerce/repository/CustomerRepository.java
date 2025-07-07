package com.boot.Ecommerce.repository;
import com.boot.Ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByNameAndEmail(String name, String email);
}
