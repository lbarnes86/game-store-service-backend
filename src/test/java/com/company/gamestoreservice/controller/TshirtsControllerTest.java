package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.models.Tshirts;
import com.company.gamestoreservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TshirtsController.class)
public class TshirtsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private Tshirts inputTshirts;
    private Tshirts outputTshirts;
    private String inputTshirtsString;
    private String outputTshirtsString;

    private List<Tshirts> allTshirts;
    private String allTshirtsString;
    private int tshirtsId = 1;
    private int nonExistentTshirtsId = 999;


    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        Tshirts tshirts = new Tshirts(0, "L", "yellow", "athlrtic shirt", new BigDecimal("14.99"), 20);

        Tshirts tshirts1 = new Tshirts();
        tshirts1.setSize("L");
        tshirts1.setColor("Yellow");
        tshirts1.setDescription("Ironic tshirt");
        tshirts1.setPrice(new BigDecimal("9.99"));
        tshirts1.setQuantity(999);

        Tshirts tshirts2 = new Tshirts();
        tshirts2.setSize("XXL");
        tshirts2.setColor("Orange");
        tshirts2.setDescription("Killer robot tshirt");
        tshirts2.setPrice(new BigDecimal("25.00"));
        tshirts2.setQuantity(102);

        List<Tshirts> tshirtsList = new ArrayList<>();
        tshirtsList.add(tshirts);
        tshirtsList.add(tshirts1);
        tshirtsList.add(tshirts2);
        doReturn(tshirtsList).when(serviceLayer).getAllTshirts();

        inputTshirts = new Tshirts(1, "XL", "brown", "sports hoodie", new BigDecimal("59.99"), 79);
        outputTshirts = new Tshirts(1, "XL", "brown", "sports hoodie", new BigDecimal("59.99"), 79);
        inputTshirtsString = mapper.writeValueAsString(inputTshirts);
        outputTshirtsString = mapper.writeValueAsString(outputTshirts);
        allTshirts = Arrays.asList(outputTshirts);
        allTshirtsString = mapper.writeValueAsString(allTshirts);

        when(serviceLayer.addATshirts(inputTshirts)).thenReturn(outputTshirts);
        when(serviceLayer.getAllTshirts()).thenReturn(allTshirts);
        when(serviceLayer.getATshirtsById(tshirtsId)).thenReturn(outputTshirts);
    }

    @Test
    public void shouldGetAllTshirts() throws Exception {
        Tshirts tshirts = new Tshirts();
        tshirts.setSize("M");
        tshirts.setColor("blue");
        tshirts.setDescription("swim wear");
        tshirts.setPrice(new BigDecimal("12.99"));
        tshirts.setQuantity(20);

        Tshirts tshirts1 = new Tshirts();
        tshirts1.setSize("L");
        tshirts1.setColor("Yellow");
        tshirts1.setDescription("Ironic tshirt");
        tshirts1.setPrice(new BigDecimal("9.99"));
        tshirts1.setQuantity(999);

        Tshirts tshirts2 = new Tshirts();
        tshirts2.setSize("XXL");
        tshirts2.setColor("Orange");
        tshirts2.setDescription("Killer robot tshirt");
        tshirts2.setPrice(new BigDecimal("25.00"));
        tshirts2.setQuantity(102);

        List<Tshirts> tshirtsList = new ArrayList<>();
        tshirtsList.add(tshirts);
        tshirtsList.add(tshirts1);
        tshirtsList.add(tshirts2);
        doReturn(tshirtsList).when(serviceLayer).getAllTshirts();
        String tshirtsJson = mapper.writeValueAsString(tshirtsList);

        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(tshirtsJson));

    }

    @Test
    public void shouldCreateTshirt() throws Exception {
        mockMvc.perform(post("/tshirts")
                        .content(inputTshirtsString)                           // Set the request body.
                        .contentType(MediaType.APPLICATION_JSON))    // Tell the server it's in JSON format.
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTshirtsString));

    }

    @Test
    public void shouldGetTshirtById() throws Exception {
        Tshirts tshirts = new Tshirts();
        tshirts.setId(1);
        tshirts.setSize("XL");
        tshirts.setColor("brown");
        tshirts.setDescription("sports hoodie");
        tshirts.setPrice(new BigDecimal("59.99"));
        tshirts.setQuantity(79);


        String tshirtsJson = mapper.writeValueAsString(tshirts);

        mockMvc.perform(get("/tshirts/" + tshirtsId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(tshirtsJson));

    }

    @Test
    public void shouldUpdateTshirt() throws Exception {
        Tshirts outputTshirts = new Tshirts();
        outputTshirts.setId(1);
        outputTshirts.setSize("S");
        outputTshirts.setColor("Pink");
        outputTshirts.setDescription("Hello Kitty");
        outputTshirts.setPrice(new BigDecimal("15.00"));
        outputTshirts.setQuantity(100);


        mockMvc.perform(put("/tshirts/" + tshirtsId)
                        .content(outputTshirtsString)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTshirt() throws Exception {
        mockMvc.perform(delete("/tshirts/" + tshirtsId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
