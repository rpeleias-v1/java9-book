package com.rodrigopeleias.subscriber;

import com.rodrigopeleias.main.WSPrefeitura;
import com.rodrigopeleias.model.NF;

import java.util.concurrent.Flow;

public class NFSubscriber implements Flow.Subscriber<NF> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(NF nf) {
        WSPrefeitura.emit(nf);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Todas as notas foram emitidas");
    }
}
