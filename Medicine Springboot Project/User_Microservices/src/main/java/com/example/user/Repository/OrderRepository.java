package com.example.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
