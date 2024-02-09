package ozdaldw.OnlineBookstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ozdaldw.OnlineBookstore.entity.Book;
import ozdaldw.OnlineBookstore.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private List<Book> allBooks;
    private HashMap<String, Book> AllBooks;
    private boolean flag=false;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.allBooks = bookRepository.findAll();
        this.AllBooks = new HashMap<String, Book>();
    }

    public List<Book> getAllBooks(){
        return allBooks;
    }

    private void insertToMap(){
        for (int i = 0; i < allBooks.size(); i++) {
            AllBooks.put(allBooks.get(i).getTitle().toLowerCase(), allBooks.get(i));
        }
        this.flag=true;
    }

    public HashMap<String, Book> getAllBooksForSearching(){
        if(flag==false)
            insertToMap();
        return AllBooks;
    }

}
