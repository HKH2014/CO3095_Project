package com.example.auctionhouse_webapplication.InitialDataLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.auctionhouse_webapplication.dto.RegistrationDto;
import com.example.auctionhouse_webapplication.dto.RegistrationDtoWithRoles;
import com.example.auctionhouse_webapplication.model.RoleEnum;
import com.example.auctionhouse_webapplication.service.UserService;

@Component
public class AdminCreationRunner implements CommandLineRunner {

    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AdminCreationRunner.class);

    private String defaultAdminUsername;
    private String defaultAdminPassword;

    @Autowired
    public AdminCreationRunner(UserService userService,
                               @Value("${default.admin.username:admin}") String defaultAdminUsername,
                               @Value("${default.admin.password:admin}") String defaultAdminPassword) {
        this.userService = userService;
        this.defaultAdminUsername = defaultAdminUsername;
        this.defaultAdminPassword = defaultAdminPassword;
    }

    @Override
    public void run(final String... args) {
        try {
            RegistrationDtoWithRoles registrationDto = new RegistrationDtoWithRoles();
            registrationDto.setUsername(defaultAdminUsername);
            registrationDto.setPassword(defaultAdminPassword);
            registrationDto.setIsAdmin(true);
            registrationDto.setIsSeller(true);

            userService.createWithAdminPrivileges(registrationDto);
            logger.info("Created default admin account.");
        } catch (Exception e) {
            logger.warn("Did not create admin account as it probably already exist.");
        }
    }

}