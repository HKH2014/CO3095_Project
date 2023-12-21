package com.example.auctionhouse_webapplication;

import com.example.auctionhouse_webapplication.controller.MessageController;
import com.example.auctionhouse_webapplication.dto.MessageDto;
import com.example.auctionhouse_webapplication.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.security.test.context.support.WithMockUser;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    @WithMockUser(roles = "ADMIN") //
    public void testSendMessageWithAuthenticatedUser() throws Exception {
        MessageDto messageDto = new MessageDto();

        String messageDtoJson = new ObjectMapper().writeValueAsString(messageDto);

        mockMvc.perform(post("/messages/send")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(messageDtoJson))
                .andExpect(status().isOk());


    }
}
