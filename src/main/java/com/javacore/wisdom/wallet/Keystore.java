package com.javacore.wisdom.wallet;

import com.google.gson.Gson;
import com.javacore.wisdom.crypto.AESManage;
import com.javacore.wisdom.crypto.Argon2id;
import com.javacore.wisdom.crypto.Keccak;
import com.javacore.wisdom.crypto.KeyPair;
import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.UUID;

public class Keystore {
    public String address;
    public Crypto crypto;
    public String id;
    public String version;
    public Kdfparams kdfparams;
    public String mac;
    public String kdf;
    private static final int saltLength = 32;
    private static final int ivLength = 16;
    public static Keystore unmarshal(String in) throws com.google.gson.JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(in, Keystore.class);
    }

    public String marshal(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Keystore fromPassword(String password) throws Exception{
        byte[] keyPair = KeyPair.GetKeyPair();
        byte[] salt = new byte[saltLength];
        byte[] iv = new byte[ivLength];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        Argon2id argon2id = new Argon2id();
        argon2id.setSalt(salt);
        AESManage aes = new AESManage(iv);

        byte[] derivedKey = argon2id.hash(password.getBytes());
        byte[] cipherPrivKey = aes.encrypt(derivedKey, keyPair);
        byte[] c = new byte[derivedKey.length + cipherPrivKey.length];
        System.arraycopy(derivedKey, 0, c, 0, derivedKey.length);
        System.arraycopy(cipherPrivKey, 0, c, derivedKey.length, cipherPrivKey.length);
        byte[] mac = Keccak.Keccak512(c);

        Keystore ks = new Keystore();
        ks.id = UUID.randomUUID().toString();
        ks.kdf = "argon2id";
        ks.kdfparams = new Kdfparams();
        ks.kdfparams.memoryCost = 20480;
        ks.kdfparams.timeCost = 4;
        ks.kdfparams.parallelism = 2;
        ks.kdfparams.salt = Hex.encodeHexString(salt);
        ks.version = "1";
        ks.mac = Hex.encodeHexString(mac);
        ks.crypto = new Crypto();
        ks.crypto.cipher = "aes-256-ctr";
        ks.crypto.ciphertext = Hex.encodeHexString(cipherPrivKey);
        ks.crypto.cipherparams = new Cipherparams();
        ks.crypto.cipherparams.iv = Hex.encodeHexString(iv);
        return null;
    }

    public boolean verifyPassword(String password) throws Exception{
        // 验证密码是否正确 计算 mac
        Argon2id argon2id = new Argon2id();
        argon2id.setSalt(Hex.decodeHex(this.kdfparams.salt.toCharArray()));
        byte[] derivedKey = argon2id.hash(password.getBytes());
        byte[] cipherPrivKey = Hex.decodeHex(this.crypto.ciphertext.toCharArray());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(derivedKey);
        output.write(cipherPrivKey);
        byte[] out = output.toByteArray();
        byte[] mac = Keccak.Keccak512(out);
        return Hex.encodeHexString(mac).equals(this.mac);
    }

    public byte[] decrypt(String password) throws Exception{
        if (!this.verifyPassword(password)){
            throw new Exception("invalid password");
        }
        Argon2id argon2id = new Argon2id();
        argon2id.setSalt(Hex.decodeHex(this.kdfparams.salt.toCharArray()));
        byte[] derivedKey = argon2id.hash(password.getBytes());
        byte[] cipherPrivKey = Hex.decodeHex(this.crypto.ciphertext.toCharArray());
        byte[] iv = Hex.decodeHex(this.crypto.cipherparams.iv.toCharArray());
        AESManage aes = new AESManage(iv);
        return aes.decrypt(derivedKey, Hex.decodeHex(this.crypto.ciphertext.toCharArray()));
    }
}









