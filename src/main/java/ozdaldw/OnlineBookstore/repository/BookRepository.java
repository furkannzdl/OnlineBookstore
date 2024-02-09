package ozdaldw.OnlineBookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ozdaldw.OnlineBookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom query methods here if needed
}

