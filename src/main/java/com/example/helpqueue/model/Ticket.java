package com.example.helpqueue.model;



import javax.persistence.*;
import java.util.Objects;

public class Ticket {


    private Long id;

    private String title;

    private Long authorID;

    private String description;

    private String time_stamp;

    private String status;

    public Ticket(){    }

    public Ticket(Long id, String title, Long authorID, String description, String time_stamp, String status) {
        this.id = id;
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.time_stamp = time_stamp;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long author) {
        this.authorID = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String timeStamp) {
        this.time_stamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id) &&
                Objects.equals(title, ticket.title) &&
                Objects.equals(authorID, ticket.authorID) &&
                Objects.equals(description, ticket.description) &&
                Objects.equals(time_stamp, ticket.time_stamp) &&
                Objects.equals(status, ticket.status);
    }

}
