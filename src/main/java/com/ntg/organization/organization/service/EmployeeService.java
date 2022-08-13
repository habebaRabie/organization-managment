package com.ntg.organization.organization.service;

import java.util.ArrayList;
import java.util.List;

import com.ntg.organization.organization.dto.EmployeeRequest;
import com.ntg.organization.organization.dto.EmployeeResponse;
import com.ntg.organization.organization.exception.EmployeeNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.organization.organization.entity.Employee;
import com.ntg.organization.organization.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeResponse> getAllEmployee() {
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		List<EmployeeResponse> employeeResponses = null;

		if(!employeeList.isEmpty()){
			employeeResponses = new ArrayList<>();
			EmployeeResponse empRes ;
			for(Employee employee: employeeList){
				empRes = new EmployeeResponse();
				BeanUtils.copyProperties(employee, empRes);
				employeeResponses.add(empRes);
			}
			return employeeResponses;
		}
		throw new EmployeeNotFoundException();
	}

	public Employee createNewEmployee(EmployeeRequest EmpReq) {

		if (EmpReq != null) {
			Employee newEmp = new Employee();
			newEmp = new Employee();
			newEmp.setId(EmpReq.getId());
			newEmp.setName(EmpReq.getName());
			newEmp.setEmail(EmpReq.getEmail());
			newEmp.setDepartment(EmpReq.getDepartment());
			return employeeRepository.save(newEmp);
		}
		throw new EmployeeNotFoundException();
	}

	public boolean deleteEmployeeById(Long id) {

		if (id != null) {

			employeeRepository.deleteById(id);
			return true;
		}

		return false;
	}

	public Employee getEmployeeByName(String name, String email) {
		if(employeeRepository.findByNameAndEmail(name, email) != null){
			return employeeRepository.findByNameAndEmail(name, email);
		}
		throw new EmployeeNotFoundException();
	}

	public Employee updateEmployeeById(EmployeeRequest employeeRequest, Long id) {
		Employee newEmp = null;
		if(employeeRequest != null && employeeRepository.existsById(id)){
			newEmp = new Employee();
			newEmp.setId(id);
			newEmp.setName(employeeRequest.getName());
			newEmp.setEmail(employeeRequest.getEmail());
			newEmp.setDepartment(employeeRequest.getDepartment());
			return employeeRepository.save(newEmp);
		}
		throw new EmployeeNotFoundException();
	}
}
