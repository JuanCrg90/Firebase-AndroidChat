package me.juancrg90.android.androidchat.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.juancrg90.android.androidchat.R;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.ediTxtPasswordt)
    EditText ediTxtPasswordt;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void handleSignin() {
        Log.e("AndroidChat", inputEmail.getText().toString());
    }

    @OnClick(R.id.btnSignup)
    public void handleSignup() {
        Log.e("AndroidChat", inputEmail.getText().toString());
    }
}
