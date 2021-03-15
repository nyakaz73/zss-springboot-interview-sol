package com.zss.interview.springbootrestapi.controllers;

import com.zss.interview.springbootrestapi.dto.BookDTO;
import com.zss.interview.springbootrestapi.dto.TransactionDTO;
import com.zss.interview.springbootrestapi.dto.TransactionResponseDTO;
import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Transaction;
import com.zss.interview.springbootrestapi.services.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO){
        Book newBook = bookService.saveBook(bookDTO);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(
          this.bookService.getBooks()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "id") Long id){
        Book book = this.bookService.getBook(id);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable(value = "id") Long id){
        List<Book> books = this.bookService.getBooksByCategory(id);
        return  ResponseEntity.ok().body(books);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable(value = "id") Long id){
        log.info("Id exists>>>: " + id);
        Book book = this.bookService.updateBook(bookDTO,id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable(value = "id") Long id){
         this.bookService.deleteBook(id);
         return ResponseEntity.ok().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<TransactionResponseDTO> purchaseBook (@RequestBody TransactionDTO transactionDTO){
        TransactionResponseDTO response = bookService.purchaseBook(transactionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
