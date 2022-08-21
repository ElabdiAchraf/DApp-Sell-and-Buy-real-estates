package com.example.smartcontract.constant;

import com.example.smartcontract.contracts.test.ImmobillierContract;



public  class  LoadedContarct {
    private static ImmobillierContract loadedContarct ;


    public static ImmobillierContract getLoadedContarct() {
        return loadedContarct;
    }

    public static void setLoadedContarct(ImmobillierContract loadedContarct) {
        LoadedContarct.loadedContarct = loadedContarct;
    }
}
