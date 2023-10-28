package utn.book_store.service;

import utn.book_store.models.Book;

import java.util.List;

public interface IBookService {

    public List<Book> getBooks();

    public Book getBookById(Integer idBook);

    public void saveBook(Book book);

    public void deleteBook(Book book);
}
