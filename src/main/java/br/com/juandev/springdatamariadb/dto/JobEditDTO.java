package br.com.juandev.springdatamariadb.dto;

import br.com.juandev.springdatamariadb.entity.SeniorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEditDTO {

    private String jobName;

    private String description;

    private SeniorityEnum seniority;

    private BigDecimal salary;

}
