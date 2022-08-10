package com.ntg.organization.organization.service;

import com.ntg.organization.organization.dto.DepartmentRequest;
import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.entity.Employee;
import com.ntg.organization.organization.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    public Department createNewDepartment(DepartmentRequest departmentRequest) {

        if (departmentRequest != null) {
            Department newDept = new Department();
            newDept.setDeptName(departmentRequest.getDepartmentName());
            return departmentRepository.save(newDept);
        }
        return null;
    }

    public boolean deleteDepartmentById(Long id) {

        if (id != null) {

            departmentRepository.deleteById(id);
            return true;
        }

        return false;
    }

//    public Department updateDepartmentName(String name){
//        return departmentRepository.updateByDeptName(name);
//    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDeptName(name);
    }
}
