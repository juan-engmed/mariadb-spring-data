package br.com.juandev.springdatamariadb.controller;

import br.com.juandev.springdatamariadb.dto.EmployeeDTO;
import br.com.juandev.springdatamariadb.entity.Employee;
import br.com.juandev.springdatamariadb.exception.SearchNotFoundException;
import br.com.juandev.springdatamariadb.repository.EmployeeRepository;
import br.com.juandev.springdatamariadb.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    private final JobRepository jobRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(@RequestBody EmployeeDTO employeeDTO){

        var job = jobRepository.findById(employeeDTO.getJobId())
                .orElseThrow(() -> new SearchNotFoundException("Job", employeeDTO.getJobId()));

        var employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEducation(employeeDTO.getEducation());
        employee.setJob(job);

        employeeRepository.save(employee);

    }

    @GetMapping("/all/{salary}")
    public List<EmployeeDTO> getAllEmployeesBySalary(@PathVariable("salary") BigDecimal salary){
        var employees = employeeRepository.findAllByJobSalaryGreaterThanEqual(salary);

        return employees.stream()
                .map(this::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .name(employee.getName())
                .education(employee.getEducation())
                .jobId(employee.getJob().getId())
                .jobName(employee.getJob().getJobName())
                .build();
    }

}
