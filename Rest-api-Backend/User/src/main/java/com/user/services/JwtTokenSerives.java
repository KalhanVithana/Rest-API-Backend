package com.user.services;

import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtTokenSerives {

	public String issueToken(String login) throws NoSuchAlgorithmException {
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			String token = JWT.create().withIssuer(login).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean verifyToken(String token, String login) {
		try {
			if (token != null) {
				Algorithm algorithm = Algorithm.HMAC256("secret");
				JWTVerifier verifier = JWT.require(algorithm).withIssuer(login).build(); // Reusable verifier instance
				DecodedJWT jwt = verifier.verify(token);
				if (jwt.getIssuer() != null) {
					return true;
				}
			}
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		return false;
	}

}
