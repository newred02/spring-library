package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.BookDto;

@Builder
@Getter
@Setter
public class ResponseOfBook {

    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;

    public static ResponseOfBook from(BookDto bookDto) {
        return ResponseOfBook.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .classification(bookDto.getClassification())
                .status(bookDto.getStatus())
                .build();
    }

}
