package br.com.juandev.springdatamariadb.service;

import br.com.juandev.springdatamariadb.dto.JobEditDTO;
import br.com.juandev.springdatamariadb.dto.JobEmployeeDTO;
import br.com.juandev.springdatamariadb.dto.JobSalaryEditDTO;
import br.com.juandev.springdatamariadb.entity.Employee;
import br.com.juandev.springdatamariadb.entity.Job;
import br.com.juandev.springdatamariadb.exception.SearchNotFoundException;
import br.com.juandev.springdatamariadb.repository.EmployeeRepository;
import br.com.juandev.springdatamariadb.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void saveJob(JobEditDTO jobEditDTO) {

        var job = new Job(jobEditDTO);

        jobRepository.save(job);

    }

    @Transactional
    public JobEditDTO updateJob(Long jobId, JobEditDTO jobEditDTO){

        var job = jobRepository.findById(jobId).orElseThrow(() -> new SearchNotFoundException("Job", jobId));

        var jobSaved = jobRepository.save(convertJobEditDTOTJob(jobEditDTO, job));

        return JobEditDTO.builder()
                .jobName(jobSaved.getJobName())
                .description(jobSaved.getDescription())
                .seniority(jobSaved.getSeniority())
                .salary(jobSaved.getSalary())
                .build();
    }

    private Job convertJobEditDTOTJob(JobEditDTO jobEditDTO, Job job){

        job.setJobName( jobEditDTO.getJobName());
        job.setDescription(jobEditDTO.getDescription());
        job.setSeniority(jobEditDTO.getSeniority());
        job.setSalary(jobEditDTO.getSalary());

        return job;
    }


    public JobEditDTO updateSalaryJob(Long jobId, JobSalaryEditDTO jobSalaryEditDTO) {
        var job = jobRepository.findById(jobId).orElseThrow(() -> new SearchNotFoundException("Job", jobId));

        job.setSalary(jobSalaryEditDTO.getSalary());

        var jobSaved = jobRepository.save(job);

        return JobEditDTO.builder()
                .jobName(jobSaved.getJobName())
                .description(jobSaved.getDescription())
                .seniority(jobSaved.getSeniority())
                .salary(jobSaved.getSalary())
                .build();
    }

    public void deleteJob(Long jobId) {
        var job = jobRepository.findById(jobId).orElseThrow(() -> new SearchNotFoundException("Job", jobId));

        jobRepository.deleteById(jobId);
    }

    public List<JobEmployeeDTO> getAllEmployeesByJob(Long jobId) {
        var employees = employeeRepository.findAllByJobId(jobId);

        return employees.stream()
                .map(this::convertEmployeeToJobEmployeeDTO)
                .collect(Collectors.toList());
    }

    private JobEmployeeDTO convertEmployeeToJobEmployeeDTO(Employee employee) {

        return JobEmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .education(employee.getEducation())
                .build();
    }
}
