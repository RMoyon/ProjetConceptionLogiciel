package uqam.projetconceptionlogiciel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.R;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UserDAL;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Retrofit.APIError.UserAPIError;


public class LoginActivity extends AppCompatActivity {


    private IUserDAL userDAL = new UserDAL();

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        TextView button = (TextView) findViewById(R.id.create_account);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        try {
            authentificate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void authentificate() throws InterruptedException {

        userDAL.authentificateUser(mEmailView.getText().toString(), mPasswordView.getText().toString())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<User>>() {
            @Override
            public void accept(Response<User> response) {
                IUserAPIError apiError = new UserAPIError(response);
                if (apiError.authTokensAreInvalid()) {
                    System.out.println("Une erreur est survenue");
                    Toast.makeText(LoginActivity.this, "Mauvais login ou mot de passe", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println("apiError null");
                    Toast.makeText(LoginActivity.this, "Connexion réussi", Toast.LENGTH_LONG).show();
                    LoginActivity.this.finish();
                }

            }
        });
    }
}

