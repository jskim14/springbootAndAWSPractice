package com.practice.book.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@WebMvcTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello_Return() throws Exception {
        String hello = "hello!!!!!!!!!!!!!!!!!!";

        mockMvc.perform(
                get("/hello")
        ).andExpect(status().isOk())
        .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_리턴() throws Exception {
        String hello = "test";
        int amount = 1000;

        mockMvc.perform(
                get("/hello/dto")
                        .param("name", hello)
                        .param("amount", String.valueOf(amount))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(hello)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
