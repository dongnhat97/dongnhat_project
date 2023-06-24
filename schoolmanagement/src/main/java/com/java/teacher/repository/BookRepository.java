package com.java.teacher.repository;

import com.java.teacher.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value="select * from book b\n" +
            "inner join teacher t\n" +
            "on b.teacher_id = t.id" +
            " where t.id= :id",nativeQuery=true)
    List<Book> getBookByTeacherId(Integer id);
}
