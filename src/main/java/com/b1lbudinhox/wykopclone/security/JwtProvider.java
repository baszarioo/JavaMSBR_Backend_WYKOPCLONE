package com.b1lbudinhox.wykopclone.security;

import com.b1lbudinhox.wykopclone.exceptions.SpringAppException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtEncoder jwtEncoder;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUsername(principal.getUsername());
    }

    private String generateTokenWithUsername(String username) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
                .subject(username)
                .claim("scope", "ROLE_USER")
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

//    private KeyStore keyStore;
//    @PostConstruct
//    public void init() {
//        try {
//            keyStore = KeyStore.getInstance('JKS');
//            InputStream resourceAsStream = getClass()
//                    .getResourceAsStream("/springblog.jks");
//            keyStore.load(resourceAsStream, "secret".toCharArray());
//        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
//            throw new SpringAppException("Exception occured while loading keystore");
//        }
//    }
}
