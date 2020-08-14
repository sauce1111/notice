package com.sauce.notice.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TestControllerTest extends TestCase {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIsReturned() throws Exception {
        String test = "test";

        mvc.perform(get("/test"))
            .andExpect(status().isOk())
            .andExpect(content().string(test));
    }

}