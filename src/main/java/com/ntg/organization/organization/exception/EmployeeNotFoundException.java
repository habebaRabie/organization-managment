package com.ntg.organization.organization.exception;

public class EmployeeNotFoundException extends RuntimeException implements EntityNotFoundException {

    public EmployeeNotFoundException() {
        super();
    }
    public EmployeeNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
