package project.ece301.mantracker.CreateAccount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phonenumber;
    private CreateAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

//        username = findViewById(R.id.username);
//        password = findViewById(R.id.password);
//        email = findViewById(R.id.email);
//        phonenumber = findViewById(R.id.phonenumber);
//        findViewById(R.id.button).setOnClickListener(v -> validateCredentials());

        presenter = new CreateAccountPresenter(this, new CreateAccountInteractor());
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(),
                password.getText().toString(), email.getText().toString(),
                phonenumber.getText().toString());
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
