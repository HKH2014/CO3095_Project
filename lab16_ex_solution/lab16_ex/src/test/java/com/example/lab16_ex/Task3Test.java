package com.example.lab16_ex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Task3Test {

    @Test
    public void classesCreated() throws Exception {
        checkExists("src/main/java/com/example/lab16_ex/controller/MainController.java");
    }

    private void checkExists(String path) {
        assertTrue(path + " is missing", Files.exists(Paths.get(path)));
    }

    //If the line below has the exception "Could not autowire. No beans of 'MockMvc' type found."
    //but the tests still run, this is likely a glitch in IntelliJ which you can ignore
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingGETShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("start"));
    }
    @Test
    public void greetingPOSTShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("start"));
    }
}
