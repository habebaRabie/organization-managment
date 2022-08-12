package com.ntg.organization.organization.service;

import com.ntg.organization.organization.dto.DepartmentRequest;
import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.entity.Employee;
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

    public List<DepartmentRequest> getAllDepartments() {
        List<Department> depts =  (List<Department>) departmentRepository.findAll();
        List<DepartmentRequest> deptsRequestList = null;

        if(!depts.isEmpty()){
            deptsRequestList = new ArrayList<>();
            DepartmentRequest deptReq;
            for(Department department: depts){
                deptReq = new DepartmentRequest();
                BeanUtils.copyProperties(department, deptReq);
                deptsRequestList.add(deptReq);
            }
        }
        return deptsRequestList;
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

    public Department updateDepartmentById(DepartmentRequest departmentRequest, Long id) throws Exception {
        if (departmentRequest != null && departmentRepository.existsById(id)) {
            Department newDept = new Department();
            newDept.setId(id);
            newDept.setDeptName(departmentRequest.getDepartmentName());
            return departmentRepository.save(newDept);
        }
        throw new Exception();
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDeptName(name);
    }
}
