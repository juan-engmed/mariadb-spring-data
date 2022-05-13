package br.com.juandev.springdatamariadb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String name;

    private String education;

    private Long jobId;

    private String jobName;
}
