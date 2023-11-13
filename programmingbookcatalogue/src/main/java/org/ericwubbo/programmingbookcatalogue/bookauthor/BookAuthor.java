package org.ericwubbo.programmingbookcatalogue.bookauthor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ericwubbo.programmingbookcatalogue.book.Book;
import org.ericwubbo.programmingbookcatalogue.author.Author;

@Entity
@NoArgsConstructor
@Getter
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JsonManagedReference
    private Author author;

    private int position;

    public BookAuthor(Book book, Author author, int position) {
        this.book = book;
        this.author = author;
        this.position = position;
    }
}
