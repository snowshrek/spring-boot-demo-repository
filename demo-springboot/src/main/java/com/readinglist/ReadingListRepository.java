package com.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David on 2019/10/15.
 */
public interface ReadingListRepository extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);
}
