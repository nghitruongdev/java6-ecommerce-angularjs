package com.fpoly.java6asm.rest.repository;

import com.fpoly.java6asm.rest.entity.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
