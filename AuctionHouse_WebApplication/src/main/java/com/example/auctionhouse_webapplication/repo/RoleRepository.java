package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Role;
import com.example.auctionhouse_webapplication.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}