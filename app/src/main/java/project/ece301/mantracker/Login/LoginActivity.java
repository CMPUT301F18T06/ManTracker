package project.ece301.mantracker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import project.ece301.mantracker.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText username;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        findViewById(R.id.btn_confirm).setOnClickListener(v -> validateCredentials());

        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showUsernameNotUniqueError() {
        username.setError(getString(R.string.username_taken_error));
    }

    @Override
    public void showUsernameInvalidError() {
        username.setError(getString(R.string.username_invalid_error));
    }

    @Override
    public void navigateToHome() {

    }
}