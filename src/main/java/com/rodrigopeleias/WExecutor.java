package com.rodrigopeleias;

import com.rodrigopeleias.model.NF;
import com.rodrigopeleias.processor.NFFilterProcessor;
import com.rodrigopeleias.subscriber.NFSubscriber;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class WExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>(executorService, 1);
        NFSubscriber subscriber = new NFSubscriber();
        NFFilterProcessor filter = new NFFilterProcessor();

        publisher.subscribe(filter);
        filter.subscribe(subscriber);

        publisher.consume(data -> System.out.println("A outra thread é " + Thread.currentThread().getName()));

        String thread = Thread.currentThread().getName();
        System.out.println("Thread principal: " + thread);
        System.out.println("Gerando a nota...");
        NF nf = new NF("Peleias", "Livro Gradle", 29.99);
        publisher.submit(nf);

        NF nf2 = new NF("Peleias", "Livro Gradle", 0);
        publisher.submit(nf2);
        System.out.println("Parabéns pela compra!");

        System.out.println("Aperte o enter para sair");
        new Scanner(System.in).nextLine();
    }
}
