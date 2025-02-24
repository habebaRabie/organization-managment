package com.ntg.organization.organization.repository;

import com.ntg.organization.organization.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    public Department findByDeptName(String name);

}
