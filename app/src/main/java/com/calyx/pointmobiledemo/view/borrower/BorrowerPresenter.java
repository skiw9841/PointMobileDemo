package com.calyx.pointmobiledemo.view.borrower;

import com.calyx.pointmobiledemo.api.model.User;
import com.calyx.pointmobiledemo.rxEventBus.RxEvent;

import io.reactivex.disposables.CompositeDisposable;

public class BorrowerPresenter implements BorrowerContract.Presenter {

    BorrowerContract.View view;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void setView(BorrowerContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {

    }

    @Override
    public void setRxEvent() {

        disposable.add(
            RxEvent.getInstance()
            .getObservable()
            .subscribe(
                object -> {
                    if(object instanceof User) {
                        view.updateView((User) object);
                    }
                }
            )
        );
    }

}
