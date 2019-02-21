package com.rodrigopeleias.main;

import com.rodrigopeleias.model.NF;

public class WSPrefeitura {

    public static void emit(NF nf) {
        try {
            String thread = Thread.currentThread().getName();
            System.out.println("emitido na thread: " + thread);
            Thread.sleep(5000);
            System.out.println("emitido!!");
        } catch (InterruptedException e) {
            System.out.println("falha ao emitir a nf");
        }
    }
}
