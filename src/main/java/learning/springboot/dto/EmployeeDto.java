package learning.springboot.dto;

import lombok.*;

@EqualsAndHashCode(exclude = {"id"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
}
