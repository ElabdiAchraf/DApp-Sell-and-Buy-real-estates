package com.example.payment_service.constant;

import com.example.payment_service.contracts.PaymentContract;



public  class  LoadedContarct {
    private static PaymentContract loadedContarct ;


    public static PaymentContract getLoadedContarct() {
        return loadedContarct;
    }

    public static void setLoadedContarct(PaymentContract loadedContarct) {
        LoadedContarct.loadedContarct = loadedContarct;
    }
}
