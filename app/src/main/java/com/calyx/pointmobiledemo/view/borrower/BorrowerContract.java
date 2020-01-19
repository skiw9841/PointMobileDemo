package com.calyx.pointmobiledemo.view.borrower;

import com.calyx.pointmobiledemo.BaseContract;
import com.calyx.pointmobiledemo.api.model.User;

public interface BorrowerContract {

    interface View extends BaseContract.View {

        void setText(String text);
        void updateView(User user);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        @Override
        void setView(BorrowerContract.View view);

        @Override
        void releaseView();

        void setRxEvent();
    }
}
