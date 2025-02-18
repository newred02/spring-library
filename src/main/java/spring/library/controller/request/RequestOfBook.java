package spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOfBook {

    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;

    public RequestOfBook(String title, String author, String publisher, Long publicationYear, String classification, String status) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.classification = classification;
        this.status = status;
    }
}
