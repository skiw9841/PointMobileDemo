package com.calyx.pointmobiledemo.view.borrowerEdit;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.calyx.pointmobiledemo.BaseActivity;
import com.calyx.pointmobiledemo.R;
import com.calyx.pointmobiledemo.api.model.User;

import butterknife.BindView;
import butterknife.OnClick;

public class BorrowerEditActivity extends BaseActivity implements com.calyx.pointmobiledemo.view.borrowerEdit.BorrowerEditContract.View {

    com.calyx.pointmobiledemo.view.borrowerEdit.BorrowerEditPresenter presenter;

    @BindView( R.id.header_name )
    TextView tvHeaderName;
    @BindView( R.id.first_name )
    EditText etFirstName;
    @BindView( R.id.middle_name )
    EditText etMiddleName;
    @BindView( R.id.last_name )
    EditText etLastName;
    @BindView( R.id.suffix )
    EditText etSuffix;
    @BindView( R.id.ssn )
    EditText etSsn;
    @BindView( R.id.date_of_birth )
    EditText etDateOfBirth;
    @BindView( R.id.best_contact )
    Spinner spBestContact;
    @BindView( R.id.marital_status )
    Spinner spMaritalStatus;
    @BindView( R.id.h_phone )
    EditText etHPhone;
    @BindView( R.id.b_phone )
    EditText etBPhone;
    @BindView( R.id.cell_phone )
    EditText etCellPhone;
    @BindView( R.id.fax )
    EditText etFax;
    @BindView( R.id.email )
    EditText etEmail;
    @BindView( R.id.street )
    EditText etStreet;
    @BindView( R.id.city )
    EditText etCity;
    @BindView( R.id.state )
    EditText etState;
    @BindView( R.id.zip )
    EditText etZip;
    @BindView( R.id.own_rent )
    Spinner spOwnRent;
    @BindView( R.id.no_of_yrs )
    EditText etNoOfYrs;

    public final static String KEY_USER = "key_user";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_edit);

        // presenter 와 연결
        presenter = new com.calyx.pointmobiledemo.view.borrowerEdit.BorrowerEditPresenter();
        presenter.setView(this);

        getUserFromIntent();
    }

    private void getUserFromIntent(){

        user = (User) getIntent().getSerializableExtra(KEY_USER);
        setTitle(user.getFullName());

        // 받아온 User 객체로 부터 View 를 초기화 해줍니다.
        initView(user);
    }

    private void initView(User user) {

        tvHeaderName.setText( "Edit Borrower" );
        etFirstName.setText( user.name.first );
        etMiddleName.setText( "" );
        etLastName.setText( user.name.last );
        etSuffix.setText( user.name.title );
        etSsn.setText( user.login.username );
        etDateOfBirth.setText( user.dateOfBirth );

        ArrayAdapter<CharSequence> bestContactAdapter = ArrayAdapter.createFromResource(this, R.array.best_contact, R.layout.spinner_text);
        bestContactAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        spBestContact.setAdapter(bestContactAdapter);
        if( user.bestContact.equals( "Cell Phone" ) ) spBestContact.setSelection( 0 );
        else if( user.bestContact.equals( "H Phone" ) ) spBestContact.setSelection( 1 );
        else if( user.bestContact.equals( "B Phone" ) ) spBestContact.setSelection( 2 );

        ArrayAdapter<CharSequence> maritalStatusAdapter = ArrayAdapter.createFromResource(this, R.array.marital_status, R.layout.spinner_text);
        maritalStatusAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        spMaritalStatus.setAdapter(maritalStatusAdapter);
        if( user.maritalStatus.equals( "" ) ) spMaritalStatus.setSelection( 0 );
        else if( user.maritalStatus.equals( "Married" ) ) spMaritalStatus.setSelection( 1 );
        else if( user.maritalStatus.equals( "Unmarried" ) ) spMaritalStatus.setSelection( 2 );
        else if( user.maritalStatus.equals( "Separated" ) ) spMaritalStatus.setSelection( 3 );

        etHPhone.setText( user.hPhone );
        etBPhone.setText( user.bPhone );
        etCellPhone.setText( user.cell );
        etFax.setText( user.fax );
        etEmail.setText( user.email );
        etStreet.setText( user.location.street1 );
        etCity.setText( user.location.city );
        etState.setText( user.location.state );
        etZip.setText( user.location.postcode );

        ArrayAdapter<CharSequence> ownRentAdapter = ArrayAdapter.createFromResource(this, R.array.own_rent, R.layout.spinner_text);
        ownRentAdapter.setDropDownViewResource(R.layout.spinner_list_text);
        spMaritalStatus.setAdapter(ownRentAdapter);
        if( user.maritalStatus.equals( "Own" ) ) spMaritalStatus.setSelection( 0 );
        else if( user.maritalStatus.equals( "Rent" ) ) spMaritalStatus.setSelection( 1 );

        etNoOfYrs.setText( "0");


    }

    @Override
    public void setText(String text) {

    }

    @OnClick(R.id.save_borrower) void onSaveClick() {

        user.name.first = etFirstName.getText().toString().trim();
        user.name.last = etLastName.getText().toString().trim();
        user.name.title = etSuffix.getText().toString().trim();
        user.ssn = etSsn.getText().toString().trim();
        user.dateOfBirth = etDateOfBirth.toString().trim();

        if( spBestContact.getSelectedItemPosition() == 0 ) user.bestContact = getString( R.string.cphone );
        else if( spBestContact.getSelectedItemPosition() == 1 ) user.bestContact = getString( R.string.hphone );
        else if( spBestContact.getSelectedItemPosition() == 2 ) user.bestContact = getString( R.string.bphone );

        user.hPhone = etHPhone.getText().toString().trim();
        user.bPhone = etBPhone.getText().toString().trim();
        user.cell = etCellPhone.getText().toString().trim();
        user.fax = etFax.getText().toString().trim();
        user.email = etFax.getText().toString().trim();
        user.location.street1 = etStreet.getText().toString().trim();
        user.location.city = etCity.getText().toString().trim();
        user.location.state = etState.getText().toString().trim();
        user.location.postcode = etZip.getText().toString().trim();

        presenter.saveClickEvent( user );
        finish();
    }
}
