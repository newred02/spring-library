package spring.library.dto;

import lombok.*;
import spring.library.controller.request.RequestOfBook;
import spring.library.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .build();
    }

    public static BookDto from(RequestOfBook request) {
        return BookDto.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .classification(request.getClassification())
                .status(request.getStatus())
                .build();
    }

    public static List<BookDto> from(List<Book> books) {
        return books.stream().map(BookDto::from).collect(Collectors.toList());
    }
}
