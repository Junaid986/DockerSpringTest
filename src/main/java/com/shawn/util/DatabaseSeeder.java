package com.shawn.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shawn.model.entity.Book;
import com.shawn.service.BookService;

import lombok.NonNull;

@Component
public class DatabaseSeeder implements CommandLineRunner {
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		bookService.saveBook(Book.builder().name("Thrones").author("Martin").price(200.0).topic("Epic").build());
//		bookService.saveBook(Book.builder().name("Ring").author("Flower").price(300.0).topic("Horror").build());		
	}

}
