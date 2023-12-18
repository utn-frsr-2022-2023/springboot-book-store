package utn.book_store;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import utn.book_store.views.BookForm;

import java.awt.EventQueue;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext springContext =
				new SpringApplicationBuilder(BookStoreApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		// Form Execution
		EventQueue.invokeLater(() -> {
			BookForm bookForm = springContext.getBean(BookForm.class);
			bookForm.setVisible(true);
		});
	}
}
