package com.risheek.libraryapi;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Integer id){
        super ("Book not found with id " + id);
    }
}
