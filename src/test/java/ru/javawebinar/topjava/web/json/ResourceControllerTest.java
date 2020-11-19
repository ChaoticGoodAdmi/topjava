package ru.javawebinar.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    void testResources() throws Exception {
        perform(get("/resources/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/css;charset=UTF-8"));
    }
}
