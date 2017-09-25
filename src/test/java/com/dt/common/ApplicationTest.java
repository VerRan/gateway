package com.dt.common;

import com.dt.common.controllers.CustController;
import com.dt.common.controllers.IndexController;
import com.dt.common.service.CustService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by VerRan.Liu on 2017/9/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = {IndexController.class, CustController.class})
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustService custService;
    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("API")))
                .andDo(document("home"
                        //, responseFields(
                       // fieldWithPath("").description("首页")
                )) ;
    }
}
