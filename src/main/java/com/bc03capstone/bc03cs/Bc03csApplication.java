package com.bc03capstone.bc03cs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.crypto.SecretKey;

@EnableCaching
@SpringBootApplication
public class Bc03csApplication {

	public static void main(String[] args) {
//		Tạo key dùng mã hóa cho token
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		String strKey = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("key: " + strKey);
		SpringApplication.run(Bc03csApplication.class, args);
	}

}
