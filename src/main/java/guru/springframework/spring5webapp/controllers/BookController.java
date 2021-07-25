package guru.springframework.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5webapp.repositories.BookRepository;

@Controller
public class BookController {
	
	private final BookRepository bookRepository;	

	// Constructor to inject BookRepository into BookController
	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}


	@RequestMapping( "/books")					// when action against /books - this method is invoked
	public String getBooks( Model model ) {		// Model von Springframework.ui
												// Model is returned to the View (MVC)
		
	//	model.addAttribute(attributeName, attributeValue)
		model.addAttribute( "books", bookRepository.findAll() );
		
		return "books";	
	}
}
