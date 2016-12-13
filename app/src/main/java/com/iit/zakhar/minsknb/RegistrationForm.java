package com.iit.zakhar.minsknb;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationForm extends BaseActivity implements View.OnClickListener {

    private static final String EMAIL_EXTRA = "Email";
    private static final String PASSWORD_EXTRA = "Password";
    private static final String USERNAME_EXTRA = "Username";


    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mEmail = (EditText) findViewById(R.id.etEmailRegister);
        mPassword = (EditText) findViewById(R.id.etPasswordRegister);
        mName = (EditText) findViewById(R.id.etNameRegister);

        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnReg:
                Intent intent = new Intent();
                intent.putExtra(EMAIL_EXTRA, mEmail.getText().toString())
                        .putExtra(PASSWORD_EXTRA, mPassword.getText().toString())
                        .putExtra(USERNAME_EXTRA, mName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
