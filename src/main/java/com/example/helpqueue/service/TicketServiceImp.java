package com.example.helpqueue.service;

import com.example.helpqueue.model.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TicketServiceImp implements TicketService{

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8081/tickets/";

    @Override
    public boolean createTicket(Ticket ticket) {
        ResponseEntity<String> result = restTemplate.postForEntity(baseUrl, ticket, String.class);
        return 201 == result.getStatusCodeValue();
    }

    @Override
    public List<Ticket> getTickets(String status) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (status != null){ baseUrl += "?status=" + status; }

        String result = restTemplate.getForObject(baseUrl, String.class);

        return objectMapper.readValue(result, new TypeReference<>(){});
    }

    @Override
    public Ticket getTicket(Long id) {
        return restTemplate.getForObject(baseUrl + id, Ticket.class);
    }

    //TODO
    @Override
    public void updateTicket(Ticket ticket) {
        restTemplate.put(baseUrl, ticket);
    }

    //TODO
    @Override
    public void deleteTicket(Long id) {
        restTemplate.delete(baseUrl + id);
    }
}
