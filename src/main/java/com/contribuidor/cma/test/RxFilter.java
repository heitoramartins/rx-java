package com.contribuidor.cma.test;

import rx.Observable;

public class RxFilter {

    public static void main (String [] args){
        Observable.just(2,3,5,8)
                .filter(number -> number % 2 == 0)
                .subscribe(System.out::println);
    }

}
