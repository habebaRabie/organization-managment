package com.ntg.organization.organization.service;

import com.ntg.organization.organization.dto.DepartmentRequest;
import com.ntg.organization.organization.dto.DepartmentResponse;
import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.exception.DepartmentNotFoundException;
import com.ntg.organization.organization.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentResponse> getAllDepartments() {
        List<Department> depts =  (List<Department>) departmentRepository.findAll();
        List<DepartmentResponse> deptsResponseList = null;

        if(!depts.isEmpty()){
            deptsResponseList = new ArrayList<>();
            DepartmentResponse deptRes;
            for(Department department: depts){
                deptRes = new DepartmentResponse();
                BeanUtils.copyProperties(department, deptRes);
                deptsResponseList.add(deptRes);
            }
        }
        return deptsResponseList;
    }

    public Department createNewDepartment(DepartmentRequest departmentRequest) {

        if (departmentRequest != null) {
            Department newDept = new Department();
            newDept.setDeptName(departmentRequest.getDeptName());
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

    public Department updateDepartmentById(DepartmentRequest departmentRequest, Long id) throws DepartmentNotFoundException {
        if (departmentRequest != null && departmentRepository.existsById(id)) {
            Department newDept = new Department();
            newDept.setId(id);
            newDept.setDeptName(departmentRequest.getDeptName());
            return departmentRepository.save(newDept);
        }
        throw new DepartmentNotFoundException();
    }

    public Department getDepartmentByName(String name) {
        if(departmentRepository.findByDeptName(name)!= null){
            return departmentRepository.findByDeptName(name);
        }
        throw new DepartmentNotFoundException();
    }
}
