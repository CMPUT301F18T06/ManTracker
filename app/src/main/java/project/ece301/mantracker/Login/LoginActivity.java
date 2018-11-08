package project.ece301.mantracker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import project.ece301.mantracker.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.btn_confirm).setOnClickListener(v -> validateCredentials());

        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString(),
                "", ""); //TODO: implement login (currently a copy of create account)
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showUsernameNotUniqueError() {
//        username.setError(getString(R.string.username_taken_error));
    }

    @Override
    public void showUsernameError() {
//        username.setError(getString(R.string.username_error));
    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showEmailError() {

    }

    @Override
    public void showPhoneError() {

    }

    @Override
    public void navigateToHome() {

    }
}
