package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.RequestOfBook;
import spring.library.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;

    @OneToMany(
            mappedBy = "book",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.REMOVE}
    )
    private List<LoanAndReturn> loanAndReturns = new ArrayList<>();

    public static Book from(RequestOfBook request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .classification(request.getClassification())
                .status(request.getStatus())
                .build();
    }

    public void update(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publicationYear = bookDto.getPublicationYear();
        this.classification = bookDto.getClassification();
        this.status = bookDto.getStatus();
    }

}
