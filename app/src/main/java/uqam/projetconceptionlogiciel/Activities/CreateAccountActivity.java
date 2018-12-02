package uqam.projetconceptionlogiciel.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uqam.projetconceptionlogiciel.R;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.APIError.UserAPIError;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UserDAL;


public class CreateAccountActivity extends AppCompatActivity {

    private IUserDAL userDAL = new UserDAL();

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText firstName;
    private EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        try {
            PostUserMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void PostUserMethod() throws InterruptedException {
        User newUser = new User(mEmailView.getText().toString(), mPasswordView.getText().toString(), lastName.getText().toString(), firstName.getText().toString());

        userDAL.createUser(newUser).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<User>>() {
            @Override
            public void accept(Response<User> response) throws Exception {
                IUserAPIError apiError = new UserAPIError(response);
                if (response.isSuccessful()) {
                    System.out.println("Utilisateur ajouté !");
                    Toast.makeText(CreateAccountActivity.this, "Utilisateur ajouté !", Toast.LENGTH_LONG).show();
                    CreateAccountActivity.this.finish();
                } else {
                    System.out.println("Une erreur est survenue, votre email est peut-être déjà utilisé");
                    Toast.makeText(CreateAccountActivity.this, "Une erreur est survenue, votre email est peut-être déjà utilisé", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

