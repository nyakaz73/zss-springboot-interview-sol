package com.zss.interview.springbootrestapi.services.book;

import com.zss.interview.springbootrestapi.dto.BookDTO;
import com.zss.interview.springbootrestapi.dto.TransactionDTO;
import com.zss.interview.springbootrestapi.dto.TransactionResponseDTO;
import com.zss.interview.springbootrestapi.enums.ExtendedTypes;
import com.zss.interview.springbootrestapi.enums.Types;
import com.zss.interview.springbootrestapi.exceptions.ResourceNotFoundException;
import com.zss.interview.springbootrestapi.models.Book;
import com.zss.interview.springbootrestapi.models.Category;
import com.zss.interview.springbootrestapi.models.Transaction;
import com.zss.interview.springbootrestapi.repositories.BookRepository;
import com.zss.interview.springbootrestapi.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Value("${transaction.base}")
    private String transactionUri;

    @Value("${transaction.token}")
    private String transactionToken;

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository){
        this.bookRepository  = bookRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Book saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        log.info(bookDTO.getCategoryId().toString());
        Optional<Category> optionalCategory = categoryRepository.findById(bookDTO.getCategoryId());
        optionalCategory.ifPresent(book::setCategory);
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return this.bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found")
        );
    }

    @Override
    public List<Book> getBooksByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category Id does not exist")
        );
        return this.bookRepository.findBooksByCategory(category);
    }

    @Override
    public Book updateBook(BookDTO bookDTO, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(bookDTO.getCategoryId());
        return this.bookRepository.findById(id)
                .map(book ->{
                    optionalCategory.ifPresent(book::setCategory);
                    book.setDescription(bookDTO.getDescription());
                    book.setPrice(bookDTO.getPrice());
                    book.setTitle(bookDTO.getTitle());
                    return this.bookRepository.save(book);
                }).orElseThrow(
                () -> new ResourceNotFoundException("Book not found")
        );
    }

    @Override
    public void deleteBook(Long id) {
        Book book  = this.bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found")
        );

        bookRepository.deleteById(id);
    }

    @Override
    public TransactionResponseDTO purchaseBook(TransactionDTO transactionDTO) {
        log.info("Logging transaction");
        log.info(Types.TYPE.name());
        Transaction transaction = new Transaction();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        final String uri = transactionUri;
        final String bearerToken = "Bearer"+" "+ transactionToken;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        transaction.setType(Types.TYPE.name());
        transaction.setExtendedType(ExtendedTypes.EXTENDED_TYPE.name());
        transaction.setAmount(transactionDTO.getAmount());
        //transaction.setCreated("2021-03-11T13:19:17.676+02:00");
        log.info("TRANSACTION DTAE.....");
        log.info(transactionDTO.getCreated().toString());
        //log.info(formatter.format(transactionDTO.getCreated()));
        //log.info("Offset date time>>>: {}", formatter.format(transactionDTO.getCreated()));
        log.info("After....");
        transaction.setCreated(formatter.format(transactionDTO.getCreated()));
        transaction.setCard(transactionDTO.getCard());
        transaction.setReference(transactionDTO.getReference());
        transaction.setNarration(transactionDTO.getNarration());
        transaction.setAdditionalData(transactionDTO.getAdditionalData());

        headers.setContentType(MediaType.APPLICATION_JSON);

        InetSocketAddress host = InetSocketAddress.createUnresolved("localhost", 8080);
        headers.setHost(host);

        headers.setContentLength(358);

        headers.add(HttpHeaders.AUTHORIZATION, bearerToken);


        //initializing CustomHeader
        HttpEntity<Transaction> requestBody = new HttpEntity<>(transaction,headers);
        log.info("Sending transaction.....");
        TransactionResponseDTO responseDTO = restTemplate.postForObject(uri, requestBody, TransactionResponseDTO.class);
        log.info("Retrieving Rersult...");
        log.info(responseDTO.toString());
        return responseDTO;
    }
}
