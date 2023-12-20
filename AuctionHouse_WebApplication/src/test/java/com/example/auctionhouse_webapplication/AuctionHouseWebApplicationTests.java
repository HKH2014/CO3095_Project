package com.example.auctionhouse_webapplication;

import com.example.auctionhouse_webapplication.dto.BidDto;
import com.example.auctionhouse_webapplication.controller.LoginController;
import com.example.auctionhouse_webapplication.controller.RegisterController;
import com.example.auctionhouse_webapplication.dto.MessageDto;
import com.example.auctionhouse_webapplication.dto.RegistrationDto;
import com.example.auctionhouse_webapplication.service.AuctionService;
import com.example.auctionhouse_webapplication.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuctionHouseWebApplicationTests {

    @Autowired
    private LoginController loginController;
    @Autowired
    private RegisterController registerController;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() {
    }


    @Test
    void testLogin() {
        String viewName = loginController.showLoginForm();

        assertEquals("login", viewName);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void testAdminRegistrationView() {
        Model model = new ExtendedModelMap();
        String viewName = registerController.showRegisterAdminForm(model);

        assertEquals("register_admin", viewName);
        assertTrue(model.containsAttribute("registration"));
    }



    @Test
    public void testAccessingHomePage() {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Welcome to Auctionhouse website"));
    }


}









