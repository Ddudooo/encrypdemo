package com.encrypt.demo.domain.sample.api;

import com.encrypt.demo.domain.sample.dto.CreateSampleDto;
import com.encrypt.demo.domain.sample.dto.SampleDto;
import com.encrypt.demo.domain.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SampleApi {

    private final SampleService service;

    @GetMapping("/samples")
    public ResponseEntity<Page<SampleDto>> getSamples(Pageable pageable) {
        return ResponseEntity.accepted().body(service.page(pageable));
    }

    @PostMapping("/samples/create")
    public ResponseEntity<Long> create(@RequestBody CreateSampleDto createSampleDto) {
        return ResponseEntity.accepted().body(service.create(createSampleDto));
    }
}
