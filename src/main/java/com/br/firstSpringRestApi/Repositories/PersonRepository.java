package com.br.firstSpringRestApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.firstSpringRestApi.Models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
