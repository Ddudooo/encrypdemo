package com.encrypt.demo.domain.sample.dto;

import com.encrypt.demo.domain.sample.domain.SampleDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SampleDto {

    private Long id;
    private String important;
    private String something;
    private String anotherThing;
    private String notImportant;

    public SampleDto(SampleDomain domain) {
        important = domain.getImportant();
        something = domain.getSomething();
        anotherThing = domain.getAnotherThing();
        notImportant = domain.getNotImportant();
    }
}
