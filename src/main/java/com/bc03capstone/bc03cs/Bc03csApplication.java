package com.bc03capstone.bc03cs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import javax.crypto.SecretKey;

@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SecurityScheme(name = "Authorization", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class Bc03csApplication {
	public static void main(String[] args) {
//		Tạo key dùng mã hóa cho token
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		String strKey = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("key: " + strKey);
		SpringApplication.run(Bc03csApplication.class, args);
	}
}
