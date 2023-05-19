package hoa.demo.cm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoa.demo.cm.data.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
