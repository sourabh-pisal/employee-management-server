package learning.springboot.controller;

import learning.springboot.dto.EmployeeDto;
import learning.springboot.dto.ErrorDto;
import learning.springboot.exception.ResourceNotFoundException;
import learning.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public EmployeeDto getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDto> handleDataNotFoundException(Exception exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), new HttpHeaders(), NOT_FOUND);
    }
}
