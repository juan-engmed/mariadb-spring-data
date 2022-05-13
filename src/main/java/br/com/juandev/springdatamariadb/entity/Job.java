package br.com.juandev.springdatamariadb.entity;

import br.com.juandev.springdatamariadb.dto.JobEditDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "job")
@DynamicInsert
@DynamicUpdate
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    private String description;

    @Enumerated(EnumType.STRING)
    private SeniorityEnum seniority;

    private BigDecimal salary;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employee = new HashSet<>();

    public Job(JobEditDTO registerDTO){
        this.jobName = registerDTO.getJobName();
        this.description = registerDTO.getDescription();
        this.seniority = registerDTO.getSeniority();
        this.salary = registerDTO.getSalary();
    }
}
