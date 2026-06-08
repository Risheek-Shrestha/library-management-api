package com.risheek.libraryapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryManagementRepository extends JpaRepository<Book, Integer> {
}
