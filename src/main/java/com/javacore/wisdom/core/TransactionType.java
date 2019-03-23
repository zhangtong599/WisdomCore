package com.javacore.wisdom.core;

@SuppressWarnings("unused")
public class TransactionType {

    //transfer wdc as normal
    private static final int TRANSFER_WDC_TYPE = 0x01;

    //use for the multi signature scene,n of m
    private static final int MULTISIG_TYPE=0x02;

    //user defined asset
    private static final int ASSET_TYPE = 0x03;


    //store data permanently
    private static final int STORAGE_DATA = 0x04;


    private static final int MULTI_CONDITION=0x05;

    //issue a vote transaction
    private static final int VOTE_TYPE=0x06;


    private static final int INCUBATE_SERVICE_TYPE=0x07;


    //merge incubate service and join to incubate
    private static final int INCUBATE_JOIN_TYPE=0x08;


    //cancel previous transaction but not save to block
    private static final int CANCEL_TRAN_TYPE=0x09;



}
