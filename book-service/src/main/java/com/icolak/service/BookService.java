package com.icolak.service;

import com.icolak.dto.BookDto;
import com.icolak.dto.BookIdDto;
import com.icolak.exception.BookNotFoundException;
import com.icolak.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book ->
                        new BookDto(
                                book.getId(),
                                book.getTitle(),
                                book.getBookYear(),
                                book.getAuthor(),
                                book.getPressName()
                        )
                )
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + isbn));
    }

    public BookDto findById(Integer id) {
        return bookRepository.findById(id)
                .map(book ->
                        new BookDto(
                                book.getId(),
                                book.getTitle(),
                                book.getBookYear(),
                                book.getAuthor(),
                                book.getPressName()
                        )
                )
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: " + id));
    }
}