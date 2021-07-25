package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	
	
	public BootStrapData( AuthorRepository authorRepository, 
						  BookRepository bookRepository, 
						  PublisherRepository publisherRepository) {
		super();
		this.authorRepository 	 = authorRepository;
		this.bookRepository 	 = bookRepository;
		this.publisherRepository = publisherRepository;
	}




	@Override
	public void run(String... args) throws Exception {
		
		System.out.println( "Started in Bootstrap" );
		
		Publisher publisher = new Publisher();
		publisher.setName( "SFG Publishing" );
		publisher.setCity( "St. P" );
		publisher.setState( "FL" );
		
		publisherRepository.save( publisher );
		
				
		Author eric = new Author( "Eric", "Evans" );
		Book ddd = new Book( "Domain Driven Design","isbn 12345" );
		eric.getBooks().add( ddd );
		ddd.getAuthors().add( eric );
		
		ddd.setPublisher( publisher );			// Achtung: Der Publisher muss dem Buch hinzugefügt werden.
		publisher.getBooks().add( ddd );		// Achtung: Das Buch muss dem Publisher hinzugefügt werden
		
		authorRepository.save( eric );
		bookRepository.save( ddd );
	
		
		Author rod = new Author( "Rod", "Johnson");
		Book NoEJB = new Book( "NoEJB", "isbn 12346" );
		rod.getBooks().add( NoEJB );
		NoEJB.getAuthors().add( rod );
		
		NoEJB.setPublisher( publisher );
		publisher.getBooks().add( NoEJB );
		
		authorRepository.save( rod );
		bookRepository.save( NoEJB );
		
		
		System.out.println( "Number of Books: " + bookRepository.count() );
        System.out.println( "Publisher Number of Books: " + publisher.getBooks().size() );
	}

}
