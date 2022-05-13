package br.com.juandev.springdatamariadb.dto;

import br.com.juandev.springdatamariadb.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEmployeeDTO {

    private Long id;

    private String name;

    private String education;

}
