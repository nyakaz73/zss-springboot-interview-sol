package com.zss.interview.springbootrestapi.services.book;

import com.zss.interview.springbootrestapi.dto.BookDTO;
import com.zss.interview.springbootrestapi.dto.TransactionDTO;
import com.zss.interview.springbootrestapi.dto.TransactionResponseDTO;
import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Category;
import com.zss.interview.springbootrestapi.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    public Book saveBook(BookDTO bookDTO);

    public List<Book> getBooks();

    public Book getBook(Long id);

    public List<Book> getBooksByCategory(Long categoryId);

    public Book updateBook(BookDTO bookDTO, Long id);

    public void deleteBook(Long id);

    public TransactionResponseDTO purchaseBook(TransactionDTO transaction);
}
