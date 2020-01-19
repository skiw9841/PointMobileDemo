package com.calyx.pointmobiledemo.view.pipeline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.calyx.pointmobiledemo.BaseActivity;
import com.calyx.pointmobiledemo.R;
import com.calyx.pointmobiledemo.adapter.PipelineAdapter;
import com.calyx.pointmobiledemo.api.model.User;
import com.calyx.pointmobiledemo.view.borrower.BorrowerActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class PipelineActivity extends BaseActivity
        implements PipelineContract.View, PipelineAdapter.OnItemClickListener {

    private PipelineAdapter adapter = new PipelineAdapter();
    private PipelinePresenter presenter = new PipelinePresenter();

    @BindView(R.id.pipeline_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress_view)
    ProgressBar progressBar;
    @BindView( R.id.header_name )
    TextView header;
    @BindView( R.id.search_by_btn )
    ImageButton searchByBtn;
    @BindView( R.id.search_by_text )
    TextView searchByText;
    @BindView( R.id.search_edit )
    EditText searchEdit;
    @BindView( R.id.search_option )
    LinearLayout searchOption;
    @BindView( R.id.search_option_text )
    TextView searchOptionText;
    @BindView( R.id.search_btn )
    ImageButton searchBtn;
    @BindView( R.id.header_menu_btn )
    ImageView menuBtn;
    @BindView( R.id.add_loan_bt)
    ImageView addLoan;
    @BindView( R.id.last_update )
    TextView lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipeline );

        // ui init
        header.setText("PIPELINE");
        searchByText.setText( "ALL" );

        // recycler view 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        // presenter 와 연결
        presenter.setView(this);

        // 랜덤 유저 데이터를 받아옵니다.
        presenter.loadData();

        // RxEventBus 를 연결합니다.
        presenter.setRxEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // presenter 와의 연결을 해제합니다.
        presenter.releaseView();
    }

    @Override
    public void onClick(User user) {

        // 어탭터 리스터로 아이템 클릭 시 BorrowerActivity 화면으로 넘어감.
        Intent intent = new Intent(this, BorrowerActivity.class);
        intent.putExtra(BorrowerActivity.KEY_USER, user);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItems(ArrayList<User> items) {
        adapter.setItems(items);
    }

    @Override
    public void updateView(User user) {

        adapter.updateView(user);
    }

}
