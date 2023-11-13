package org.ericwubbo.programmingbookcatalogue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
