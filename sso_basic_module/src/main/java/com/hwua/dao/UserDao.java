package com.hwua.dao;

import com.hwua.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, String> {
}
