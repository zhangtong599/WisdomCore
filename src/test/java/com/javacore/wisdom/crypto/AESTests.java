package com.javacore.wisdom.crypto;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AESTests {
    private static final String testIV         = "5faf6f9e553054d1a51846da5137abc0";
    private static final String testKey        = "6368616e676520746869732070617373";
    private static final String testPlainText  = "yongyang2018";
    private static final String testCipherText = "4b30f2b3de178345cf9fa0fc";

    @Test
    public void encrypt() throws Exception{
        AESManage aes = new AESManage(Hex.decodeHex(testIV.toCharArray()));
        byte[] encrypted = aes.encrypt(
                Hex.decodeHex(testKey.toCharArray()), testPlainText.getBytes()
        );
        assert Hex.encodeHexString(encrypted).equals(testCipherText);
    }

    @Test
    public void decrypt() throws Exception{
        AESManage  aes = new AESManage(Hex.decodeHex(testIV.toCharArray()));
        byte[] decrypted = aes.encrypt(
                Hex.decodeHex(testKey.toCharArray()), Hex.decodeHex(testCipherText.toCharArray())
        );
        assert new String(decrypted).equals(testPlainText);
    }
}
