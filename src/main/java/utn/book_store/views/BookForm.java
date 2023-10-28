package utn.book_store.views;

import org.springframework.beans.factory.annotation.Autowired;
import utn.book_store.service.BookService;

import javax.swing.*;

public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;

    @Autowired
    public BookForm(BookService bookService){
        this.bookService = bookService;
        initializeForm();
    }

    private void initializeForm(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
    }
}
