package com.example.auctionhouse_webapplication;


import com.example.auctionhouse_webapplication.controller.SubscriptionController;
import com.example.auctionhouse_webapplication.dto.SubscriptionDto;
import com.example.auctionhouse_webapplication.service.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;

    @Test
    @WithMockUser // Symuluje zalogowanego użytkownika z domyślnymi uprawnieniami
    public void testSubscribe() throws Exception {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setUserId("user123");
        subscriptionDto.setAuctionId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String subscriptionDtoJson = objectMapper.writeValueAsString(subscriptionDto);

        mockMvc.perform(post("/subscriptions/subscribe")
                        .with(csrf()) // Dodaje token CSRF do żądania
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(subscriptionDtoJson))
                .andExpect(status().isOk());

        verify(subscriptionService).subscribe(any(SubscriptionDto.class));
    }
}
