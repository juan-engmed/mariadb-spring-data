package br.com.juandev.springdatamariadb.controller;

import br.com.juandev.springdatamariadb.dto.JobEditDTO;
import br.com.juandev.springdatamariadb.dto.JobEmployeeDTO;
import br.com.juandev.springdatamariadb.dto.JobSalaryEditDTO;
import br.com.juandev.springdatamariadb.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    @GetMapping("/{id}/employees")
    public List<JobEmployeeDTO> getAllEmployeesByJob(@PathVariable("id") Long jobId){
        return jobService.getAllEmployeesByJob(jobId);
    }

    @PostMapping("/register-job")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody JobEditDTO jobEditDTO){

        jobService.saveJob(jobEditDTO);
    }

    @PutMapping("/update-job/{id}")
    public JobEditDTO update(@PathVariable("id") Long jobId, @RequestBody JobEditDTO jobEditDTO){

        return jobService.updateJob(jobId, jobEditDTO);
    }

    @PatchMapping("/update-salary-job/{id}")
    public JobEditDTO update(@PathVariable("id") Long jobId, @RequestBody JobSalaryEditDTO jobSalaryEditDTO){

        return jobService.updateSalaryJob(jobId, jobSalaryEditDTO);
    }

    @DeleteMapping("/delete-job/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long jobId){
        jobService.deleteJob(jobId);
    }
}
