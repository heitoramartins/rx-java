package com.contribuidor.cma.config.security.jwt;

import com.contribuidor.cma.entities.User;
import com.contribuidor.cma.exception.fielderror.FieldErrorMessage;
import com.contribuidor.cma.exception.handler.FieldValidationException;
import com.contribuidor.cma.util.helper.RequestHelper;
import com.contribuidor.cma.util.i18n.MessageByLocaleService;
import com.contribuidor.cma.util.validate.Validator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.contribuidor.cma.exception.fielderror.FieldErrorMessage.createFieldError;
import static com.contribuidor.cma.util.validate.Validator.ensureThat;


@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUTORITHY = "aut";
    static final String CLAIM_KEY_USER_CODE = "user_code";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_USER_PROFILE = "user_profile";

    @Autowired
    private MessageByLocaleService messageService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private RequestHelper requestHelper;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(String name, User user,
                                Collection<? extends GrantedAuthority> authorities) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,name);
        claims.put(CLAIM_KEY_AUTORITHY,authorities);
        claims.put(CLAIM_KEY_USER_CODE,userCode(user.getId()));
        claims.put(CLAIM_KEY_USER_PROFILE,user.getProfile().getId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        String newToken = generateToken(claims);
        return newToken;
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Claims getClaims(String token) {
        return getClaimsFromToken(token);
    }

    public Long userCode(Long userCode) {
        Validator<FieldErrorMessage> validator = validateUser(userCode);
        if (validator.hasErrors()) {
            throw new FieldValidationException(validator.errors(), HttpStatus.BAD_REQUEST);
        }
        return userCode;
    }

    private Validator<FieldErrorMessage> validateUser(Long userCode) {
        return ensureThat()
                .assertFalse(userCode == null, createFieldError("user", messageService.getMessage("error.login.message")));
    }

    public String getCurrentToken() {
        return requestHelper.actualRequest().getHeader(this.tokenHeader);
    }
}