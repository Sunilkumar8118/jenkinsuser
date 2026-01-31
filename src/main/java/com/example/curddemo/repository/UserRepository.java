package com.example.curddemo.repository;


import com.example.curddemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}