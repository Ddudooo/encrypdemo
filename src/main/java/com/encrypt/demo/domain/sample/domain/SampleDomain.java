package com.encrypt.demo.domain.sample.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate5.type.EncryptedStringType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@TypeDef(
    name = "encryptedString",
    defaultForType = EncryptedStringType.class,
    typeClass = EncryptedStringType.class,
    parameters = {
        @Parameter(name = "algorithm", value = "PBEWithMD5AndDES"),
        @Parameter(name = "password", value = "#{systemProperties.jasypt.password:test}")
    }
)
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class SampleDomain {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "encryptedString")
    private String important;

    private String something;

    private String anotherThing;

    private String notImportant;

    public static SampleDomain create(
        String important,
        String something,
        String anotherThing,
        String notImportant) {
        SampleDomain domain = new SampleDomain();
        domain.important = important;
        domain.something = something;
        domain.anotherThing = anotherThing;
        domain.notImportant = notImportant;
        return domain;
    }
}
