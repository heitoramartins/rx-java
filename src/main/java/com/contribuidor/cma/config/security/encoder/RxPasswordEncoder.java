package com.contribuidor.cma.config.security.encoder;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RxPasswordEncoder implements PasswordEncoder {

    public static final String SALT = "9b4d2f5153c5bc199a30610cc2c8810f";
    public static final int HASH_ITERATIONS = 10000;

    @Override
    public String encode(CharSequence clearTextPassword) {
        if (clearTextPassword == null) {
            return null;
        }

        return (new Sha512Hash(clearTextPassword, ByteSource.Util.bytes(Hex.decode(SALT)), HASH_ITERATIONS)).toHex();
    }

    @Override
    public boolean matches(CharSequence clearTextPassword, String encodedPassword) {
        return encode(clearTextPassword).equals(encodedPassword);
    }
}
