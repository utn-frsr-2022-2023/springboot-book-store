package utn.book_store.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.book_store.models.Book;
import utn.book_store.service.BookService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class BookForm extends JFrame {
    BookService bookService; // The service for book-related operations
    private JPanel panel; // The main panel
    private JTable bookTable; // Table for books
    private JTextField idText;
    private JTextField bookText;
    private JTextField authorText;
    private JTextField priceText;
    private JTextField stockText;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private DefaultTableModel bookTableModel; // Table model for the bookTable

    @Autowired
    public BookForm(BookService bookService){
        this.bookService = bookService;
        initializeForm();
        addButton.addActionListener(e -> addBook());
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectedBook();
            }
        });
        updateButton.addActionListener( e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
    }

    private void initializeForm(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);

        // Get the window dimension
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int x = (dimension.width - getWidth() / 2);
        int y = (dimension.height - getHeight() / 2);
        setLocation(x,  y);
        
    }

    private void deleteBook() {

        var row = bookTable.getSelectedRow();

        if(row != -1) {
            String idBook = bookTable.getModel().getValueAt(row, 0).toString();
            var book = new Book();
            book.setIdBook(Integer.parseInt(idBook));

            bookService.deleteBook(book);
            showMessage("The book " + idBook + " has been deleted");

            cleanForm();
            getBooks();
        }

    }
    private void updateBook() {
        if (this.idText.equals("")) {
            showMessage("Please, select a record on the table");
        }
        else  {
            if (bookText.equals("")) {
                showMessage("Please, enter a title");
                bookText.requestFocusInWindow();
                return;
            }

            int idBook = Integer.parseInt(idText.getText());
            var title = bookText.getText();
            var author = authorText.getText();
            var price = Double.parseDouble(priceText.getText());
            var stock = Integer.parseInt(stockText.getText());

            var book = new Book(idBook, title, author, price, stock);
            this.bookService.saveBook(book);
            showMessage("The book has been updated");

            cleanForm();
            getBooks();
        }
    }

    private void loadSelectedBook (){
        var row = bookTable.getSelectedRow();
        if(row != -1) {
            String idBook = bookTable.getModel().getValueAt(row, 0).toString();
            idText.setText(idBook);
            String title = bookTable.getModel().getValueAt(row, 1).toString();
            bookText.setText(title);
            String author = bookTable.getModel().getValueAt(row, 2).toString();
            authorText.setText(author);
            String price = bookTable.getModel().getValueAt(row, 3).toString();
            priceText.setText(price);
            String stock = bookTable.getModel().getValueAt(row, 4).toString();
            stockText.setText(stock);

        }
    }

    private void addBook() {
        // get data from book
        if (bookText.getText().equals("")) {
            showMessage("Enter the title.");
            bookText.requestFocusInWindow();
            return;
        }

        var title = bookText.getText();
        var author = authorText.getText();
        var price = Double.parseDouble(priceText.getText());
        var stock = Integer.parseInt(stockText.getText());

        var book = new Book(null, title, author, price, stock);
        this.bookService.saveBook(book);
        showMessage("The book has been saved");

        cleanForm();
        getBooks();
    }

    private void cleanForm() {
        bookText.setText("");
        authorText.setText("");
        priceText.setText("");
        stockText.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void createUIComponents() {

        idText = new JTextField("");
        idText.setVisible(false);
        this.bookTableModel = new DefaultTableModel(0, 5){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] header = {"Id", "Book", "Author", "Price", "Stock"};
        this.bookTableModel.setColumnIdentifiers(header);

        this.bookTable = new JTable(bookTableModel);

        // Avoid multiple selection
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getBooks(); // Populate the table
    }

    private void getBooks() {
        bookTableModel.setRowCount(0); // Clear existing data

        var books = bookService.getBooks();

        // Add each book to the table
        books.forEach((book) -> {
            Object[] tableRow = {
                    book.getIdBook(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getStock(),
            };
            this.bookTableModel.addRow(tableRow);
        });
    }
}
