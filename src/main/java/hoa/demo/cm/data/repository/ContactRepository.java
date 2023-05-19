package hoa.demo.cm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoa.demo.cm.data.entity.ABContact;

public interface ContactRepository extends JpaRepository<ABContact, Long> {

}
