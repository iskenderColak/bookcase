package com.icolak.client;

import com.icolak.dto.BookDto;
import com.icolak.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", path = "/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception e) {
        logger.info("Book not found by isbn: " + isbn + ", returning default BookDto object");
        return ResponseEntity.ok(new BookIdDto(0, "default-isbn"));
    }

    @GetMapping("/book/{bookId}")
    ResponseEntity<BookDto> getBookById(@PathVariable(value = "bookId") Integer bookId);
}