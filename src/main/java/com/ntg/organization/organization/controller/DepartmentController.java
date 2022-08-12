package com.ntg.organization.organization.controller;

import com.ntg.organization.organization.dto.DepartmentRequest;
import com.ntg.organization.organization.dto.DepartmentResponse;
import com.ntg.organization.organization.entity.Department;
import com.ntg.organization.organization.entity.Employee;
import com.ntg.organization.organization.service.DepartmentService;
import com.ntg.organization.organization.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept/v1")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/all")
    public List<DepartmentResponse> getAllDepartment() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "/getByName/{name}")
    public Department getDepartmentByName(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @PostMapping(value = "/add")
    public Department createNewDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createNewDepartment(departmentRequest);
    }

    @DeleteMapping(value = "/del/{deptId}")
    public boolean deleteDepartment(@PathVariable (value = "deptId") Long id) {
        return departmentService.deleteDepartmentById(id);
    }

    @PutMapping(value = "/updateByName/{deptId}")
    public Department updateDepartmentName(@RequestBody DepartmentRequest departmentRequest, @PathVariable (value = "deptId") Long id) throws Exception {
        return departmentService.updateDepartmentById(departmentRequest, id);
    }
}
