package org.ericwubbo.programmingbookcatalogue.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ericwubbo.programmingbookcatalogue.bookauthor.BookAuthor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "book")
    private Set<BookAuthor> authors = new HashSet<>();

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(BookAuthor bookAuthor) {
        authors.add(bookAuthor);
    }

    public Long getId() {
        return id;
    }
}
