package com.example.helpqueue;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.helpqueue.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:ticket-schema.sql",
        "classpath:ticket-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TicketUnitTest {

    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper mapper;

    private final Ticket TEST_CREATE_TICKET = new Ticket(4L, "This is Ticket 4", 1L, "Description 4" , "112233", "'Active'");
    private final Ticket SAVED_TICKET = new Ticket(1L, "This is Ticket 1", 1L, "Description 1" , "112233", "'Yes'");


    @Test
    public void createTicket() throws Exception
    {
        String resultString = mockMVC.perform( MockMvcRequestBuilders
                .post("/ticket/create")
                .content(asJsonString(TEST_CREATE_TICKET))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getRequest().getContentAsString();

        Ticket result = this.mapper.readValue(resultString, Ticket.class);
        assertThat(result.equals(TEST_CREATE_TICKET));
    }


    @Test
    public void testFindAll() throws Exception {

        final String resultString = this.mockMVC
                .perform(request(HttpMethod.GET, "/ticket/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Ticket> results = Arrays.asList(mapper.readValue(resultString, Ticket[].class));
        assertThat(results.size() == 3);
        assertThat(results.get(0).getAuthorID() == 1);
        assertThat(results.get(1).getAuthorID() == 2);
        assertThat(results.get(2).getAuthorID() == 3);
    }


    @Test
    public void testFindById() throws Exception {

        final String resultString = this.mockMVC
                .perform(request(HttpMethod.GET, "/ticket/id").param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Ticket result = this.mapper.readValue(resultString, Ticket.class);
        assertThat(result.equals(SAVED_TICKET));
    }


    @Test
    public void testFindByStatus() throws Exception {

        final String resultString = this.mockMVC
                .perform(request(HttpMethod.GET, "/ticket/status").param("status", "Yes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Ticket> results = Arrays.asList(mapper.readValue(resultString, Ticket[].class));
        assertThat(results.size() == 1);
        assertThat(results.get(0).getAuthorID() == 1);
        assertThat(results.get(1).getAuthorID() == 2);
    }

    @Test
    public void testFindByAuthor() throws Exception {

        final String resultString = this.mockMVC
                .perform(request(HttpMethod.GET, "/ticket/author").param("authorID", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        List<Ticket> results = Arrays.asList(mapper.readValue(resultString, Ticket[].class));
        assertThat(results.size() == 1);
        assertThat(results.get(0).getAuthorID() == 1);
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
