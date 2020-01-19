package com.calyx.pointmobiledemo.view.pipeline;

import com.calyx.pointmobiledemo.BaseContract;
import com.calyx.pointmobiledemo.api.model.User;

import java.util.ArrayList;

public interface PipelineContract {

    interface View extends BaseContract.View  {

        void showProgress();

        void hideProgress();

        void showToast(String message);

        // 아이템을 어댑터에 연결
        void setItems(ArrayList<User> items);

        void updateView(User user);

    }

    interface Presenter extends BaseContract.Presenter<View> {

        @Override
        void setView(View view);

        @Override
        void releaseView();

        // API 통신을 통해 데이터통신
        void loadData();

        void setRxEvent();
    }
}
