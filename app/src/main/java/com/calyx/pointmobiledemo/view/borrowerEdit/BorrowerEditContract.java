package com.calyx.pointmobiledemo.view.borrowerEdit;

import com.calyx.pointmobiledemo.BaseContract;
import com.calyx.pointmobiledemo.api.model.User;

public interface BorrowerEditContract {

    interface View extends BaseContract.View {

        void setText(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        @Override
        void setView(BorrowerEditContract.View view);

        @Override
        void releaseView();

        void saveClickEvent(User user);
    }
}
