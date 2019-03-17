package learning.springboot.bootstrap;

import learning.springboot.domain.Employee;
import learning.springboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class EmployeeBootStrap {

    private final EmployeeRepository employeeRepository;

    @Bean
    public CommandLineRunner populateEmployees() {
        return args -> {
            List<Employee> employees = List.of(
                    new Employee(null, "First", "Employee"),
                    new Employee(null, "Second", "Employee"),
                    new Employee(null, "Third", "Employee"));
            employeeRepository.saveAll(employees);
        };
    }
}
