package com.example.auctionhouse_webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.auctionhouse_webapplication.model.Role;
import com.example.auctionhouse_webapplication.model.RoleEnum;
import com.example.auctionhouse_webapplication.repo.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(RoleEnum role) {
        return roleRepository.findByRoleEnum(role).orElseGet(() -> createInternalRole(role));
    }

    private Role createInternalRole(RoleEnum roleEnum) {
        Role role = new Role();
        role.setRole(roleEnum);
        return roleRepository.save(role);
    }

}
