package com.encrypt.demo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Provider;
import java.security.Security;

@SpringBootTest
class EncryptdemoApplicationTests {


    @Test
    void contextLoads() {
    }

    /**
     * 프로퍼티 암호화를 위해 테스트작성
     *
     * @param param 프로퍼티값
     * @see com.encrypt.demo.global.config.JasyptConfig
     */
    @ParameterizedTest
    @ValueSource(strings =
        {
            "jdbc:h2:tcp://localhost/~/testcrypt",
            "sa"
        }
    )
    void encryptString(String param) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getProperty("jasypt.password", "test")); //암호화에 사용할 키 -> 중요
        config.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC"); //사용할 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProvider(new BouncyCastleProvider());
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String enc = encryptor.encrypt(param); //암호화 할 내용
        System.out.println("enc = " + enc); //암호화 한 내용을 출력

        //테스트용 복호화
        String des = encryptor.decrypt(enc);
        System.out.println("des = " + des);
    }

    @Test
    public void listProviderAlgorithm() {
        Security.addProvider(new BouncyCastleProvider());
        for (Provider provider : Security.getProviders()) {
            System.out.println("Provider: " + provider.getName());
            for (Provider.Service service : provider.getServices()) {
                System.out.println("  Algorithm: " + service.getAlgorithm());
            }
        }
    }
}
