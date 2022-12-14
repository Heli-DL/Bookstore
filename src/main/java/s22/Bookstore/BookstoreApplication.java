package s22.Bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Romance"));
			
			repository.save(new Book("Harry Potter ja Viisasten kivi", "J.K. Rowling", "123456777-0", 1997, 39.90, crepository.findByName("Fantasy").get(0)));
			repository.save(new Book("The Fellowship of the Ring", "J.R.R. Tolkien", "112336777-0", 1968, 49.90, crepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			} 
		};
	}

}
