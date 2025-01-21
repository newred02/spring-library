package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.request.RequestOfBook;
import spring.library.controller.request.RequestOfMember;
import spring.library.domain.Book;
import spring.library.domain.LoanAndReturn;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanAndReturnRepository;
import spring.library.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final LoanAndReturnRepository loanAndReturnRepository;

    public List<BookDto> findAllBook(){
        List<BookDto> bookDtoList = bookRepository.findAll().stream().map(BookDto::from).toList();
        return bookDtoList;
    }

    public BookDto findBookById(Long id) {
        BookDto bookDto = BookDto.from(bookRepository.findById(id).orElse(null));
        return bookDto;
    }

    public BookDto createBook(RequestOfBook request) {
        BookDto bookDto = BookDto.from(bookRepository.save(Book.from(request)));
        return bookDto;
    }

    @Transactional
    public BookDto updateBook(Long id, RequestOfBook request) {
        Book book = bookRepository.findById(id).orElse(null);
        book.update(BookDto.from(request));
        return BookDto.from(book);
    }

    @Transactional
    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        if(book.getStatus().equals("대출중")) {
            throw new RuntimeException("대출중인 책은 삭제할 수 없습니다.");
        }
        book.setStatus("대출불가");
    }

}
