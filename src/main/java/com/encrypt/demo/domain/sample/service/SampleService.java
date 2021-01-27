package com.encrypt.demo.domain.sample.service;

import com.encrypt.demo.domain.sample.domain.SampleDomain;
import com.encrypt.demo.domain.sample.dto.CreateSampleDto;
import com.encrypt.demo.domain.sample.dto.SampleDto;
import com.encrypt.demo.domain.sample.repo.SampleJpaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SampleService {

    private final SampleJpaRepo sampleRepo;

    @Transactional
    public Long create(CreateSampleDto dto) {
        SampleDomain create = SampleDomain
            .create(dto.getImportant(), dto.getSomething(), dto.getAnotherThing(),
                dto.getNotImportant());

        return sampleRepo.save(create).getId();
    }

    public Page<SampleDto> page(Pageable pageable) {
        Page<SampleDomain> pages = sampleRepo.findAll(pageable);
        return pages.map(SampleDto::new);
    }

}
