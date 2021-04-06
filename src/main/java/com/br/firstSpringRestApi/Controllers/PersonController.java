package com.br.firstSpringRestApi.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.firstSpringRestApi.Models.Person;
import com.br.firstSpringRestApi.Repositories.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController 
{
	@Autowired //setting springboot responsible for the managment of this repository
	private PersonRepository personRepository;
	
	@GetMapping
	public List<Person> getPeople()
	{
		return personRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person savePerson(@RequestBody Person person) 
	{
		
		return personRepository.save(person);
	}
	
	@GetMapping("/{id}")
	public Optional<Person> getPersonById(@PathVariable Long id) 
	{//Optional means that id may not exist
		
		return personRepository.findById(id);
	}
	
	/*@GetMapping("/{id}")
	public Person getPersonById(@PathVariable Long id) {//Get one needs to place spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false on aplication.properties
		
		return personRepository.getOne(id);
	}*/
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long id) 
	{
	   personRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Person updatePerson(@RequestBody Person person, @PathVariable Long id)
	{
		Person p = personRepository.getOne(id);
		
		if(p == null) {
			return null;
		} 
		
		p.setName(person.getName());
		
		return personRepository.save(p);
	}

}
