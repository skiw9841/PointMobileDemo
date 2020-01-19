package com.calyx.pointmobiledemo.view.borrowerEdit;

import com.calyx.pointmobiledemo.api.model.User;
import com.calyx.pointmobiledemo.rxEventBus.RxEvent;

public class BorrowerEditPresenter implements BorrowerEditContract.Presenter {

    BorrowerEditContract.View view;

    @Override
    public void setView(BorrowerEditContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {

    }

    @Override
    public void saveClickEvent(User user) {
        // business logic - save
        // PipelineActivity과 BorrowerActivity에 이벤트 전송
        RxEvent.getInstance().sendEvent(user);
    }
}
