package com.example.auctionhouse_webapplication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.auctionhouse_webapplication.dto.RegistrationDto;
import com.example.auctionhouse_webapplication.dto.RegistrationDtoWithRoles;
import com.example.auctionhouse_webapplication.error.NonUniqueUsernameException;
import com.example.auctionhouse_webapplication.error.UserRelatedException;
import com.example.auctionhouse_webapplication.model.Role;
import com.example.auctionhouse_webapplication.model.RoleEnum;
import com.example.auctionhouse_webapplication.model.User;
import com.example.auctionhouse_webapplication.repo.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository, final RoleService roleService,
                       final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createBuyer(RegistrationDto registrationDto) {
        createUser(registrationDto, RoleEnum.BUYER);
    }

    public void createSeller(RegistrationDto registrationDto) {
        createUser(registrationDto, RoleEnum.SELLER);
    }

    public void createWithAdminPrivileges(RegistrationDtoWithRoles registrationDto) {
        List<RoleEnum> roles = new ArrayList<>();
        if (registrationDto.getIsAdmin()) {
            roles.add(RoleEnum.ADMIN);
        }
        if (registrationDto.getIsSeller()) {
            roles.add(RoleEnum.SELLER);
        }
        createUser(registrationDto, roles.toArray(new RoleEnum[0]));
    }

    User getUserOrThrow(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UserRelatedException("User does not exist."));
    }

    private void createUser(RegistrationDto registrationDto, RoleEnum... roles) {
        assertThatUsernameWouldBeUnique(registrationDto.getUsername());
        final List<Role> roleEntities = Arrays.stream(roles).map(roleService::getRole).toList();
        final User user = mapToUser(registrationDto, roleEntities);
        userRepository.save(user);
    }

    private User mapToUser(final RegistrationDto registrationDto, final List<Role> roleEntities) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setRoles(roleEntities);
        return user;
    }

    private void assertThatUsernameWouldBeUnique(String username) {
        userRepository.findByUsername(username).ifPresent(u -> {
            throw new NonUniqueUsernameException();
        });
    }

}
