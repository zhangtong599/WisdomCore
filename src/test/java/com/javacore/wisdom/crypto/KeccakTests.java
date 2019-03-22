package com.javacore.wisdom.crypto;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeccakTests {
    private static final String testbuf  = "0de77bf1e9b2c8a69bfec346d62046256571de8cb04624b57b849d8540e2d9b3f6eeb2a4cecacce32559f2ca586d405958b60b3dd5e83b8ece6797404bf29df2";
    private static final String testHash = "a34d39c08fc1b584dd8d62a1503125feea102b42f0ffc10d217ac2311065a76979fa42ca81a68fc686a6782affd4fe2967b75789630500750582d049e8a9f424";
    @Test
    public void sha3512() throws Exception{
        byte[] res = Keccak.Keccak512(Hex.decodeHex(testbuf.toCharArray()));
        assert Hex.encodeHexString(res).equals(testHash);
    }
}