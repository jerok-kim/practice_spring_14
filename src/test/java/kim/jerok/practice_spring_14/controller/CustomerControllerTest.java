package kim.jerok.practice_spring_14.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findById_test() throws Exception {
        // given
        int id = 1;

        // when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/customers/" + id);
        ResultActions actions = mockMvc.perform(builder);

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        ResultMatcher isOk = MockMvcResultMatchers.status().isOk();
        actions.andExpect(isOk);

        ResultMatcher isSameId = MockMvcResultMatchers.jsonPath("$.data.id").value("1");
        ResultMatcher isSameName = MockMvcResultMatchers.jsonPath("$.data.name").value("홍길동");
        ResultMatcher isSameTel = MockMvcResultMatchers.jsonPath("$.data.tel").value("0102222");
        actions.andExpect(isSameId);
        actions.andExpect(isSameName);
        actions.andExpect(isSameTel);
    }

    @Test
    public void findAll_test() throws Exception {
        // given
        int page = 1;

        // when
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/customers").param("page", page + "");
        ResultActions actions = mockMvc.perform(builder);

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        ResultMatcher isOk = MockMvcResultMatchers.status().isOk();
        actions.andExpect(isOk);
    }

    @Test
    public void save_test() throws Exception {
        // given
        String requestBody = "{\n\"name\":\"Jerok\",\n\"tel\":\"0101234\"\n}";

        // when
        MockHttpServletRequestBuilder builders = MockMvcRequestBuilders.post("/customers")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions actions = mockMvc.perform(builders);

        // then
        ResultMatcher isCreated = MockMvcResultMatchers.status().isCreated();
        actions.andExpect(isCreated);
    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        String requestBody = "{\n\"name\":\"Jerok\",\n\"tel\":\"0101234\"\n}";

        // when
        MockHttpServletRequestBuilder builders = MockMvcRequestBuilders.put("/customers/" + id)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions actions = mockMvc.perform(builders);

        // then
        ResultMatcher isOk = MockMvcResultMatchers.status().isOk();
        actions.andExpect(isOk);

        ResultMatcher location = MockMvcResultMatchers.header().string("Location", "/customers/" + id);
        actions.andExpect(location);
    }

    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        MockHttpServletRequestBuilder builders = MockMvcRequestBuilders.delete("/customers/" + id);
        ResultActions actions = mockMvc.perform(builders);

        // then
        ResultMatcher isOk = MockMvcResultMatchers.status().isOk();
        actions.andExpect(isOk);
    }

}
