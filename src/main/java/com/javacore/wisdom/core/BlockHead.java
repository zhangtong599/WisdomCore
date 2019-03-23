package com.javacore.wisdom.core;

import com.javacore.wisdom.account.Account;
import com.javacore.wisdom.crypto.Hash;



public class BlockHead {

    private static final int BLOCK_VERSION=1;

    private int blockNumber;

    private Hash blockHash;

    private Hash prevBlockHash;

    private Hash merkleHash;

    private Hash merkleReceipt;

    private Long timeStamp;

    private Account productionAccount;

    private Long gasUsage;

    private String witness;

    private String remark;


}

