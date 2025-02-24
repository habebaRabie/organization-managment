package com.ntg.organization.organization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ntg.organization.organization.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    public Employee findByNameAndEmail(String name, String email);

//    public Employee updateByName(String name);
}
