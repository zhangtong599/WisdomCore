package com.javacore.wisdom.keystore;


import com.javacore.wisdom.wallet.Keystore;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeystoreTests {
    private static final String testPrivKey   = "947d3ad33d2b14856d504c5c2984c1c2bb3a9d6c7b4b6307d40d45347903b33c";


    @Test
    public void keyStoreLoads(){
        Keystore ks = Keystore.unmarshal(testJson());
        assert ks.crypto != null;
        assert ks.id.equals("617ff99a-5fbe-4e0c-b39c-e6473a6bfd5e");
    }

    @Test
    public void keyStoreSave() throws Exception{
        Keystore ks = Keystore.unmarshal(testJson());
        String str = ks.marshal();
        Keystore ks2 = Keystore.unmarshal(str);
        assert ks.crypto.cipherparams.iv.equals(ks2.crypto.cipherparams.iv);
    }

    @Test
    public void fromPassword() throws Exception{
        Keystore ks = Keystore.fromPassword("yongyang2018");
    }

    @Test
    public void verifyPassword() throws Exception{
        Keystore ks = Keystore.unmarshal(testJson());
        assert ks.verifyPassword("yongyang2018");
    }

    @Test
    public void decrypt() throws Exception{
        Keystore ks = Keystore.unmarshal(testJson());
        assert Hex.encodeHexString(ks.decrypt("yongyang2018")).equals(testPrivKey);
    }

    public static String testJson(){
        return "{" +
                "  \"address\": \"WXCf8e2b617210d44ccd232ec081f17be76b3eaa6f0cb41\"," +
                "  \"crypto\": {" +
                "    \"cipher\": \"aes-256-ctr\"," +
                "    \"ciphertext\": \"e58c5cd0f07f3a080859ab69ae261d67af2aaa02347283e766870962c9844e0d\"," +
                "    \"cipherparams\": {" +
                "      \"iv\": \"2d96e310684da9c3ce87db65c5a5606c\"" +
                "    }" +
                "  }," +
                "  \"id\": \"617ff99a-5fbe-4e0c-b39c-e6473a6bfd5e\"," +
                "  \"version\": \"1\"," +
                "  \"kdf\": \"argon2id\"," +
                "  \"kdfparams\": {" +
                "    \"timeCost\": 4," +
                "    \"memoryCost\": 20480," +
                "    \"parallelism\": 2," +
                "    \"salt\": \"c5b5aef708139af895a52eef251ef7d747680ee785f30e9bc0f5c897fed2a1d0\"" +
                "  }," +
                "  \"mac\": \"b5a1e277c2d4f8947fe7c0f43430ab8c5f2df144d1691ca1fb7335c198932a4d9e269a0e5ff27bd818092bbc2c1b68df9fad4ea5e5e9f1ee6d4507b6390c1a0d\"" +
                "}";
    }
}
