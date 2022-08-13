package com.ntg.organization.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleResourceNotFound(final DepartmentNotFoundException exception) {

        return new ErrorResponse(Errors.DEPARTMENT_NOT_FOUND.getMessage(), Errors.DEPARTMENT_NOT_FOUND.getCode());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleResourceNotFound(final EmployeeNotFoundException exception) {

        return new ErrorResponse(Errors.EMPLOYEE_NOT_FOUND.getMessage(), Errors.EMPLOYEE_NOT_FOUND.getCode());
    }

}
