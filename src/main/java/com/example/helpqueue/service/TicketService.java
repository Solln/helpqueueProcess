package com.example.helpqueue.service;

import com.example.helpqueue.model.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface TicketService {

    boolean createTicket(Ticket ticket);

    Ticket getTicket(Long id) throws JsonProcessingException;

    Iterable<Ticket> getTickets(String status) throws URISyntaxException, JsonProcessingException;

    void updateTicket(Ticket ticket);

    void deleteTicket(Long id);


}
