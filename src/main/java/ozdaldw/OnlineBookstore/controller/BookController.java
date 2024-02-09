package ozdaldw.OnlineBookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ozdaldw.OnlineBookstore.entity.Book;
import ozdaldw.OnlineBookstore.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/OnlineBookstore")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/wantedbook")
    public ResponseEntity<Book> getBook(@RequestParam int id){
        List<Book> books = bookService.getAllBooks();
        System.out.println(books);
        if (id >= 0 && id <= books.size()) {
            Book wantedBook = books.get(id - 1);
            return ResponseEntity.ok(wantedBook);
        } else {
            // Handle the case where the ID is out of bounds
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchbook")
    public ResponseEntity<Book> SearchForBook(@RequestParam String title){
        HashMap<String, Book> books = bookService.getAllBooksForSearching();
        title = title.toLowerCase();
        System.out.println(title);
        if(!title.isEmpty() && books.containsKey(title)){
            Book searchedBook = books.get(title);
            return  ResponseEntity.ok(searchedBook);
        }
        return ResponseEntity.notFound().build();
    }


}
