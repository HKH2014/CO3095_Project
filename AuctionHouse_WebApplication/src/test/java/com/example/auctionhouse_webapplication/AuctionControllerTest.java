package com.example.auctionhouse_webapplication;

import com.example.auctionhouse_webapplication.controller.AuctionController;
import com.example.auctionhouse_webapplication.dto.BidDto;
import com.example.auctionhouse_webapplication.service.AuctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.security.test.context.support.WithMockUser;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;



@WebMvcTest(AuctionController.class)
public class AuctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuctionService auctionService;

    @Test
    @WithMockUser(authorities = "BUYER") // Symuluje zalogowanego użytkownika z uprawnieniem BUYER
    public void testSetMaxBid() throws Exception {
//        BidDto bidDto = new BidDto();
//        // Uzupełnij bidDto odpowiednimi danymi
//        bidDto.setUserId("user123"); // Przykładowe dane
//        bidDto.setMaxBid(100.0); // Przykładowe dane
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String bidDtoJson = objectMapper.writeValueAsString(bidDto);
//
//        mockMvc.perform(post("/auctions/1/set-max-bid")
//                        .with(csrf()) // Dodaje token CSRF do żądania
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(bidDtoJson))
//                .andExpect(status().is3xxRedirection()); // Zmieniamy oczekiwanie na przekierowanie
//
//        verify(auctionService).setMaxBid(anyInt(), any(BidDto.class));
    }
}
