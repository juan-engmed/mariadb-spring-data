package br.com.juandev.springdatamariadb.repository;

import br.com.juandev.springdatamariadb.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
}
