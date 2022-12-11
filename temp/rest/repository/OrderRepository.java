package com.fpoly.java6asm.rest.repository;

import com.fpoly.java6asm.rest.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    @RestResource (path = "byCustomer")
    List<Order> findAllByUsername(@Param ("username") String username);
}
