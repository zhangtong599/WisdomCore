package com.javacore.wisdom.crypto;

import com.kosprov.jargon2.api.Jargon2;
import org.apache.commons.codec.binary.Hex;

public class Argon2id{
    private byte[] salt;
    private static final int memoryCost = 20480;
    private static final int timeCost = 4;
    private static final int parallelism = 2;
    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] hash(byte[] in){
        String password = Hex.encodeHexString(salt) + Hex.encodeHexString(in);
        return Jargon2.jargon2Hasher().type(Jargon2.Type.ARGON2id).memoryCost(memoryCost)
                .timeCost(timeCost).parallelism(parallelism).salt(salt)
                .password(password.getBytes()).rawHash();
    }
}