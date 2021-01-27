package com.encrypt.demo.domain.sample.repo;

import com.encrypt.demo.domain.sample.domain.SampleDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleJpaRepo extends JpaRepository<SampleDomain, Long> {

}
