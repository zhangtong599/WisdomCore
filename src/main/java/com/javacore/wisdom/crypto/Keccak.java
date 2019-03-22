package com.javacore.wisdom.crypto;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.KeccakDigest;

public class Keccak {
    public static byte[] Keccak512(byte[] in){
        Digest digest = new KeccakDigest(512);
        digest.update(in,0, in.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return rsData;
    }
}
