package utn.book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.book_store.models.Book;

public interface BookRepository extends JpaRepository <Book, Integer>{

}
