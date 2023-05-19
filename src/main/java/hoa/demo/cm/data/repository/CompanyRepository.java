package hoa.demo.cm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoa.demo.cm.data.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
