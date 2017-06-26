package com.shawn.repository;

import com.shawn.model.entity.BookStore;
import com.shawn.model.entity.BookStoreWithBooks;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author Xiaoyue Xiao
 */
public interface BookStoreRepository {

    BookStore selectBookStoreById(Long id);

    List<BookStore> selectAllBookStores();

    BookStoreWithBooks selectBookStoreWithBooksById(Long id);

}
