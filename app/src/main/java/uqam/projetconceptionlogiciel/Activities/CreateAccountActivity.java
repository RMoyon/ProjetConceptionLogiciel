package uqam.projetconceptionlogiciel.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uqam.projetconceptionlogiciel.DAL.IUniversityDAL;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.R;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.APIError.UserAPIError;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UniversityDAL;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UserDAL;


public class CreateAccountActivity extends AppCompatActivity {

    private IUserDAL userDAL = new UserDAL();
    IUniversityDAL universityDAL = new UniversityDAL();
    List ListUniversities = new ArrayList();
    ArrayAdapter adapter;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText firstName;
    private EditText lastName;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        spinner = (Spinner) findViewById(R.id.spinner);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        this.adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                this.ListUniversities
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        this.GetAllUniversities();
    }

    private void GetAllUniversities() {
        universityDAL.getAllUniversities()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<List<University>>>() {
            @Override
            public void accept(Response<List<University>> response) {
                Integer i;
                for (i = 0; i< response.body().size(); i++) {
                    CreateAccountActivity.this.ListUniversities.add(response.body().get(i).getName());
                    CreateAccountActivity.this.adapter = new ArrayAdapter(
                            CreateAccountActivity.this,
                            android.R.layout.simple_spinner_item,
                            CreateAccountActivity.this.ListUniversities
                    );
                }
            }
                });
    }

    private void validate() {
        try {
            PostUserMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void PostUserMethod() throws InterruptedException {
        if (mEmailView.getText().toString().isEmpty() || mPasswordView.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(CreateAccountActivity.this, "Un ou plusieurs champs vide", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            User newUser = new User(mEmailView.getText().toString(), mPasswordView.getText().toString(), lastName.getText().toString(), firstName.getText().toString());

            userDAL.createUser(newUser).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Response<User>>() {
                @Override
                public void accept(Response<User> response) throws Exception {
                    IUserAPIError apiError = new UserAPIError(response);
                    if (response.isSuccessful()) {
                        System.out.println("Utilisateur ajouté !");
                        Toast toast = Toast.makeText(CreateAccountActivity.this, "Utilisateur ajouté !", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        CreateAccountActivity.this.finish();
                    } else {
                        System.out.println("Une erreur est survenue, votre email est peut-être déjà utilisé");
                        Toast toast = Toast.makeText(CreateAccountActivity.this, "Une erreur est survenue, votre email est peut-être déjà utilisé", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                }
            });
        }
    }
}

