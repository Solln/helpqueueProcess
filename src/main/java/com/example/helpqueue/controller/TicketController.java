package com.example.helpqueue.controller;

import com.example.helpqueue.model.Ticket;
import com.example.helpqueue.service.TicketServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;


// use tickets, as opposed to ticket
@RestController
@RequestMapping(path="/tickets")
public class TicketController {

    /*
    EFFECT WHOLE COLLECTION
    /tickets

     SINGLE RECORD IN COLLECTION
    /tickets/{id}
     */

    @Autowired
    TicketServiceImp ticketService;

    // Assume POST is create, don't require /create
    @PostMapping("")
    public ResponseEntity<Boolean> createTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    // Assume empty get gets all tickets
    //?status=true || active - inactive
    // if not get all
    @GetMapping("")
    public @ResponseBody ResponseEntity<Iterable<Ticket>> getAllTickets(@RequestParam(name="status", required=false) String status) throws JsonProcessingException {
        return ResponseEntity.ok(ticketService.getTickets(status));
    }

    //tickets/11
    @GetMapping(path="/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("")
    public @ResponseBody ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        ticketService.updateTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


}
