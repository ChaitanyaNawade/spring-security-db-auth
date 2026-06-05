package com.chaitanya.SecurityImpl03.repositories;

import com.chaitanya.SecurityImpl03.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Integer>
{
  Optional<MyUser> findByUsername(String username);
}
