package es.dmunozfer.jenkins;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class HolaControllerTest {

    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
	mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void raiz() throws Exception {
	mockMvc.perform(get("/"))
	.andExpect(status().is3xxRedirection())
	.andExpect(header().string("Location", "/hola"));
    }
    
    @Test
    public void holaSinParametros() throws Exception {
	mockMvc.perform(get("/hola"))
	.andExpect(status().isOk())
	.andExpect(view().name("hola"))
	.andExpect(model().attributeExists("nombre"))
	.andExpect(model().attribute("nombre", is("Mundo")))
	.andExpect(content().string(containsString("¡Hola Mundo!")))
	;
    }
    
    @Test
    public void holaDavid() throws Exception {
	mockMvc.perform(get("/hola?nombre=David"))
	.andExpect(status().isOk())
	.andExpect(view().name("hola"))
	.andExpect(model().attributeExists("nombre"))
	.andExpect(model().attribute("nombre", is("David")))
	.andExpect(content().string(containsString("¡Hola David!a")))
	;
    }

}
