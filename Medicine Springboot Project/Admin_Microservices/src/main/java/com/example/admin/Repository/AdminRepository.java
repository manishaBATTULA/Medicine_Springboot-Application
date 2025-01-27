package com.example.admin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admin.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}
