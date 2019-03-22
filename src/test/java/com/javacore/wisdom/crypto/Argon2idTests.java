package com.javacore.wisdom.crypto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.codec.binary.Hex;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Argon2idTests {

    private static final String testSalt       = "9222d54ab57dd12fef0418945ea6595f2db36df75aafdbae208b358509945f00";
    private static final String testDerivedKey = "8cd0da07cf3b04c0342bca2f641a19c483caf28dc03304887741f47e62ee3f09";
    private static final String testPassword   = "yongyang2018";

    @Test
    public void argon2idHash() throws Exception{
        Argon2id argon2id = new Argon2id();
        byte[] salt = Hex.decodeHex(testSalt.toCharArray());
        argon2id.setSalt(salt);
        String res = Hex.encodeHexString(argon2id.hash(testPassword.getBytes()));
        assert testDerivedKey.equals(res);
    }
}
