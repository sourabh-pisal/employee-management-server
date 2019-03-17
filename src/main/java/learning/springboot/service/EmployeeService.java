package learning.springboot.service;

import learning.springboot.domain.Employee;
import learning.springboot.dto.EmployeeDto;
import learning.springboot.exception.ResourceNotFoundException;
import learning.springboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeService {
    private static final String EMPLOYEE_NOT_FOUND_ERROR_MSG = "Employee %s Does Not Exists";
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAllByOrderByIdAsc()
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::getEmployeeDto)
                .collect(Collectors.toList());
    }

    private EmployeeDto getEmployeeDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto getEmployee(Long id) {
        return getEmployeeDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format(EMPLOYEE_NOT_FOUND_ERROR_MSG, id))));
    }
}
