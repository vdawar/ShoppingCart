package com.dawar.vipul.repository;

import com.dawar.vipul.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
