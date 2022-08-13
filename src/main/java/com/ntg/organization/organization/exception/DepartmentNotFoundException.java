package com.ntg.organization.organization.exception;

public class DepartmentNotFoundException extends RuntimeException  implements EntityNotFoundException {
    public DepartmentNotFoundException(){
        super();
    }
    public DepartmentNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
