package com.contribuidor.cma.test;

import rx.Observable;

public class RxMap {

    public static void main (String [] args){
        Observable<Integer> obs1 = Observable.create(subscriber -> {
            System.out.println("Evento emitido...");

            subscriber.onNext(10);
            subscriber.onNext(20);
            subscriber.onNext(30);

            System.out.println("Finalizando eventos...");
            subscriber.onCompleted();

        });

        obs1.map(number -> number + number)
            .map(number -> number / 2)
            .filter(number -> number % 2 == 0)
            .filter(number -> number < 30)
            .subscribe(System.out::println);
    }

}
