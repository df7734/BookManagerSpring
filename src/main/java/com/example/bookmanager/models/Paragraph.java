package com.example.bookmanager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paragraph {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String paragraphName;

    public Paragraph(String paragraphName) {
        this.paragraphName = paragraphName;
    }

    public Paragraph() {
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "id=" + id +
                ", paragraphName='" + paragraphName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParagraphName() {
        return paragraphName;
    }

    public void setParagraphName(String paragraphName) {
        this.paragraphName = paragraphName;
    }
}
