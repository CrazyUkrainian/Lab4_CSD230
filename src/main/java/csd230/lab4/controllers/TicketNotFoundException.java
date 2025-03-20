package csd230.lab4.controllers;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Could not find ticket " + id);
    }
}
