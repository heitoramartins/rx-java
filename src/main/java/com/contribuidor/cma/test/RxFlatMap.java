package com.contribuidor.cma.test;

import rx.Observable;

public class RxFlatMap {

    public static void main (String [] args){
        Observable<Integer> obs1 = Observable.create(subscriber -> {
            System.out.println("Evento emitido obs 1...");

            subscriber.onNext(10);
            wairFor(1000L);

            subscriber.onNext(20);
            wairFor(1000L);

            subscriber.onNext(30);

            System.out.println("Finalizando eventos obs 1...");
            subscriber.onCompleted();

        });

        Observable<Integer> obs2 = Observable.create(subscriber -> {
            System.out.println("Evento emitido obs 2...");

            subscriber.onNext(100);

            wairFor(2000L);

            subscriber.onNext(200);

            System.out.println("Finalizando eventos obs 2...");
            subscriber.onCompleted();

        });

        Observable.just(1)//1
                  .flatMap(number ->
                          obs1.map(result -> number + result)
                  )
                  .flatMap(number ->
                          obs2.map(result -> number + result)
                  )
                  .subscribe(System.out::println);

        //Primeiro evento
        //10 + 1 = 11
        //( |obs1 11| |obs2 100 + 11 = 111 , 200 + 11 = 211| )

        //Segundo evento
        //20 + 1 = 21
        //( |obs1 21| |obs2 100 + 21 = 121 , 200 + 21 = 221| )

        //Terceiro evento
        //30 + 1 = 31
        //( |obs1 31| |obs2 100 + 31 = 131 , 200 + 31 = 231| )
    }

    public static void wairFor(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
