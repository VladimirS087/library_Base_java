package com.example.demo.packages.db.repository;

import com.example.demo.packages.db.entity.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE first_name = :first_name AND last_name = :last_name AND middle_name = :middle_name")
    List<Person> findByFullName(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("middle_name") String middleName
    );


}
