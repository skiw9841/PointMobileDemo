package com.calyx.pointmobiledemo.view.pipeline;

import com.calyx.pointmobiledemo.api.GithubApi;
import com.calyx.pointmobiledemo.api.GithubApiProvider;
import com.calyx.pointmobiledemo.api.model.User;
import com.calyx.pointmobiledemo.constant.Constant;
import com.calyx.pointmobiledemo.rxEventBus.RxEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PipelinePresenter implements com.calyx.pointmobiledemo.view.pipeline.PipelineContract.Presenter {

    private GithubApi api;
    private com.calyx.pointmobiledemo.view.pipeline.PipelineContract.View view;

    private CompositeDisposable disposable;

    PipelinePresenter() {
        this.api = GithubApiProvider.provideGithubApi();
        this.disposable = new CompositeDisposable();
    }

    @Override
    public void setView(com.calyx.pointmobiledemo.view.pipeline.PipelineContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        disposable.clear();
    }

    @Override
    public void loadData() {

        disposable.add(api.getUserList(Constant.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> {
                    view.showProgress();
                })
                .doOnTerminate(() -> {
                    view.hideProgress();
                })
                .subscribe(userResponse -> {
                    view.setItems((ArrayList<User>)userResponse.userList);
                }, error -> {
                    view.showToast(error.getMessage());
                })
        );

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
