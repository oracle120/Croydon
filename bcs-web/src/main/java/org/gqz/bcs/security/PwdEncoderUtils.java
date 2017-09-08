package org.gqz.bcs.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdEncoderUtils {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
	}
}
