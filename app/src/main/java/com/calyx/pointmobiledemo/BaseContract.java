package com.calyx.pointmobiledemo;

public class BaseContract {

    public interface Presenter<T> {
        void setView(T view);
        void releaseView();
    }

    public interface View {
    }
}
