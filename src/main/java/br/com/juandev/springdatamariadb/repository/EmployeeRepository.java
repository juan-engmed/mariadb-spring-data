package br.com.juandev.springdatamariadb.repository;

import br.com.juandev.springdatamariadb.dto.JobEmployeeDTO;
import br.com.juandev.springdatamariadb.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByJobId(Long jobId);

    List<Employee> findAllByJobSalaryGreaterThanEqual(BigDecimal salary);
}
