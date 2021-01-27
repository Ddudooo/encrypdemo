package com.encrypt.demo.domain.sample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateSampleDto {

    private String important;
    private String something;
    private String anotherThing;
    private String notImportant;
}
