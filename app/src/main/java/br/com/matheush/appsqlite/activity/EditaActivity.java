package br.com.matheush.appsqlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import br.com.matheush.appsqlite.MyApplication;
import br.com.matheush.appsqlite.R;
import br.com.matheush.appsqlite.dao.PessoaDao;
import br.com.matheush.appsqlite.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditaActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.edicao_et_nome)
    EditText etNome;
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.edicao_et_num_cel)
    EditText etNumCel;
    @Email(message = MyApplication.MSG_INVALIDO)
    @BindView(R.id.edicao_et_email)
    EditText etEmail;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validator = new Validator(this);
        validator.setValidationListener(this);

        Intent intent = getIntent();

        long index = intent.getExtras().getLong("index");
        Pessoa pessoa = new PessoaDao().getObejeto(index);

        etNome.setText(pessoa.getNome());
        etNumCel.setText(String.valueOf(pessoa.getNumeroCelular()));
        etEmail.setText(pessoa.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edita, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_salvar_pessoa:
                validator.validate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onValidationSucceeded() {
        //ATUALIZA DADOS DA PESSOA NO BANCO
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
