package com.encrypt.demo.global.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeApi {

    private final StringEncryptor encryptor;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/encrypt")
    public ResponseEntity<Map<String, Object>> encryptString(@RequestParam("value") String value){
        log.info("value [{}]",value);
        String encrypt = encryptor.encrypt(value);
        log.info("encrypt [{}]",encrypt);
        String decrypt = encryptor.decrypt(encrypt);
        log.info("decrypt [{}]", decrypt);
        Map<String, Object> result = new HashMap<>();
        result.put("enc", encrypt);
        result.put("dec", decrypt);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/decrypt")
    public ResponseEntity<Map<String, Object>> decryptString(@RequestParam("value") String value){
        log.info("value [{}]",value);
        String decrypt = encryptor.decrypt(value);
        log.info("encrypt [{}]",decrypt);
        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        result.put("dec", decrypt);
        return ResponseEntity.ok(result);
    }
}
