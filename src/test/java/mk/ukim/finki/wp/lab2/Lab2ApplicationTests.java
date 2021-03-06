package mk.ukim.finki.wp.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class Lab2ApplicationTests {

    MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    void contextLoads() {
    }
    @Test
    public void testCoursesPage() throws Exception {
        MockHttpServletRequestBuilder coursesRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(coursesRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));
    }

}
