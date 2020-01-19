package com.calyx.pointmobiledemo.view.borrower;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.calyx.pointmobiledemo.BaseActivity;
import com.calyx.pointmobiledemo.R;
import com.calyx.pointmobiledemo.api.model.User;
import com.calyx.pointmobiledemo.view.borrowerEdit.BorrowerEditActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BorrowerActivity extends BaseActivity implements com.calyx.pointmobiledemo.view.borrower.BorrowerContract.View {

    com.calyx.pointmobiledemo.view.borrower.BorrowerPresenter presenter;

    @BindView( R.id.header_menu_btn )
    ImageView tvMenuBtn;
    @BindView( R.id.header_name )
    TextView tvHeaderName;
    @BindView( R.id.borrower_name )
    TextView tvBorrowerName;
    @BindView( R.id.phone_btn )
    Button btnPhone;
    @BindView( R.id.text_btn )
    Button btnText;
    @BindView( R.id.email_btn )
    Button btnEmail;
    @BindView( R.id.name )
    TextView tvName;
    @BindView( R.id.ssn )
    TextView tvSsn;
    @BindView( R.id.date_of_birth )
    TextView tvDateOfBirth;
    @BindView( R.id.marital_status )
    TextView tvMaritalStatus;
    @BindView( R.id.best_contact )
    TextView tvBestContact;
    @BindView( R.id.h_phone )
    TextView tvHPhone;
    @BindView( R.id.b_phone )
    TextView tvBPhone;
    @BindView( R.id.cell_phone )
    TextView tvCellPhone;
    @BindView( R.id.fax )
    TextView tvFax;
    @BindView( R.id.email )
    TextView tvEmail;
    @BindView( R.id.address )
    TextView tvAddress;
    @BindView( R.id.own_rent )
    TextView tvOwnRent;
    @BindView( R.id.experian )
    TextView tvExperian;
    @BindView( R.id.transunion )
    TextView tvTransunion;
    @BindView( R.id.equifax )
    TextView tvEquifax;
    @BindView( R.id.base )
    TextView tvBase;
    @BindView( R.id.overtime )
    TextView tvOvertime;
    @BindView( R.id.bonuses )
    TextView tvBonuses;
    @BindView( R.id.commissions )
    TextView tvCommissions;
    @BindView( R.id.div_int )
    TextView tvDivInt;
    @BindView( R.id.income_other_1 )
    TextView tvIncomeOther1;
    @BindView( R.id.net_rent )
    TextView tvNetRent;
    @BindView( R.id.income_other_2 )
    TextView tvIncomeOther2;
    @BindView( R.id.income_total )
    TextView tvIncomeTotal;
    @BindView( R.id.rent )
    TextView tvRent;
    @BindView( R.id. first_mtg)
    TextView tvFirstMtg;
    @BindView( R.id.other_fin )
    TextView tvOtherFin;
    @BindView( R.id.hazard_ins )
    TextView tvHazardIns;
    @BindView( R.id.taxes )
    TextView tvTaxes;
    @BindView( R.id.mtg_ins )
    TextView tvMtgIns;
    @BindView( R.id.hoa_dues )
    TextView tvHoaDues;
    @BindView( R.id.expense_other )
    TextView tvExpenseOther;
    @BindView( R.id.expense_total )
    TextView tvExpenseTotal;
    @BindView( R.id.total_other_payments )
    TextView tvTotalOtherPayments;

    public final static String KEY_USER = "key_user";

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);

        // presenter 와 연결
        presenter = new com.calyx.pointmobiledemo.view.borrower.BorrowerPresenter();
        presenter.setView(this);

        presenter.setRxEvent();

        // PipelineActivity 로부터 User 객체를 받아옵니다.
        getUserFromIntent();
    }

    private void getUserFromIntent(){
        user = (User) getIntent().getSerializableExtra(KEY_USER);

        initView(user);
    }

    private void initView(User user) {

        tvBorrowerName.setText(user.getFullName());
        tvName.setText( user.getFullName() );
        tvSsn.setText( user.login.username );
        tvDateOfBirth.setText( user.dateOfBirth );
        tvMaritalStatus.setText( user.maritalStatus );
        tvBestContact.setText( user.bestContact );
        tvHPhone.setText( user.hPhone );
        tvBPhone.setText( user.bPhone );
        tvCellPhone.setText( user.cell );
        tvFax.setText( user.fax );
        tvEmail.setText( user.email );
        tvAddress.setText( user.getAddress() );
        tvOwnRent.setText( user.ownRent );
        tvExperian.setText( user.experian );
        tvTransunion.setText( user.transunion );
        tvEquifax.setText( user.equifax );
        tvBase.setText( user.base );
        tvOvertime.setText( user.overtime );
        tvBonuses.setText( user.bonuses );
        tvCommissions.setText( user.commissions );
        tvDivInt.setText( user.divInt );
        tvIncomeOther1.setText( user.incomeOther1 );
        tvNetRent.setText( user.netRent );
        tvIncomeOther2.setText( user.incomeOther2 );
        tvIncomeTotal.setText( user.incomeTotal );
        tvRent.setText( user.rent );
        tvFirstMtg.setText( user.firstMtg );
        tvOtherFin.setText( user.otherFin );
        tvHazardIns.setText( user.hazardIns );
        tvTaxes.setText( user.taxes );
        tvMtgIns.setText( user.mtgIns );
        tvHoaDues.setText( user.hoaDues );
        tvExpenseOther.setText( user.expenseOther );
        tvExpenseTotal.setText( user.expenseTotal );
        tvTotalOtherPayments.setText( user.totalOtherPayments );

    }

    @OnClick({R.id.phone_btn})
    void onPhoneClick() {
        int permissionCheck = ContextCompat.checkSelfPermission( BorrowerActivity.this, Manifest.permission.CALL_PHONE );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( BorrowerActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0 );
        } else {
            startActivity( new Intent( "android.intent.action.CALL", Uri.parse( "tel:" + user.phone ) ) );
        }
    }

    @OnClick(R.id.email_btn)
    void onEmailClick() {
        Intent emailIntent = new Intent( Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.putExtra( Intent.EXTRA_EMAIL, new String[]{user.email});
        emailIntent.putExtra( Intent.EXTRA_SUBJECT, "[CalyxSoftware]");
        startActivity( Intent.createChooser(emailIntent, "send mail"));
    }

    @OnClick(R.id.text_btn)
    void onTextClick() {
        Intent textIntent = new Intent( Intent.ACTION_SENDTO, Uri.fromParts("sms", user.cell, null));
        textIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(textIntent);
    }

    @OnClick(R.id.borrower_edit_btn)
    void onBorrowerEditClick() {
        Intent intent = new Intent(this, BorrowerEditActivity.class);
        intent.putExtra(BorrowerEditActivity.KEY_USER, user);
        startActivity(intent);
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void updateView(User user) {
        initView( user );
    }
}
