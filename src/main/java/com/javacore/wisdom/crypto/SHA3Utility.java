package com.javacore.wisdom.crypto;


import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.crypto.digests.SHAKEDigest;

public class SHA3Utility {




    /**
     * Generate hash of the given input using the given Digest.
     *
     * @param input  input data.
     * @param digest the digest to use for hashing
     * @return hashed data.
     */
    public static byte[] hash(byte[] input, Digest digest) {
        byte[] retValue = new byte[digest.getDigestSize()];
        digest.update(input, 0, input.length);
        digest.doFinal(retValue, 0);
        return retValue;
    }


    /**
     *
     * @param hashbytes
     * @return hex format string
     */
    public static String hashToHexString(byte[] hashbytes){
        return Hex.toHexString(hashbytes);
        //return Hex.toHexString(hash(param.toString().getBytes(UTF_8), new SHA3Digest()));
    }



    /*
    public static String shake128(byte[] bytes) {
        Digest digest = new SHAKEDigest(128);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHAKE-256 算法
    public static String shake256(byte[] bytes) {
        Digest digest = new SHAKEDigest(256);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }
     */


}
