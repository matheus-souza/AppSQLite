package br.com.matheush.appsqlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import br.com.matheush.appsqlite.adapter.PessoaAdapter;
import br.com.matheush.appsqlite.dao.PessoaDao;
import br.com.matheush.appsqlite.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.M;
import static com.activeandroid.Cache.getContext;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.etNome)
    EditText etNome;
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.etFone)
    EditText etFone;
    @Email(message = MyApplication.MSG_INVALIDO)
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.btSalvar)
    Button btSalvar;
    @BindView(R.id.rvLista)
    RecyclerView rvLista;

    private Validator validator;
    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvLista.setItemAnimator(new DefaultItemAnimator());
        rvLista.setHasFixedSize(true);

        atualizaDadosLista();
    }

    public void atualizaDadosLista() {
        PessoaDao pessoaDao = new PessoaDao();

        pessoas = pessoaDao.getObejetos();

        PessoaAdapter pessoaAdapter = new PessoaAdapter(MainActivity.this, pessoas, onItemClickListener(), onItemLongClickListener());
        rvLista.setAdapter(pessoaAdapter);
    }

    protected PessoaAdapter.OnItemClickListener onItemClickListener() {
        return new PessoaAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d("LogX", "Clicou no item " + position);
            }
        };
    }

    protected PessoaAdapter.OnItemLongClickListener onItemLongClickListener() {
        return new PessoaAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(final int position) {
                Log.d("LogX", "Clicou longo no item " + position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Aviso!")
                        .setMessage("Deseja apagar a pessoa " + pessoas.get(position).getNome() + "?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new PessoaDao().deleta(pessoas.get(position));
                                atualizaDadosLista();
                            }
                        }).setNegativeButton("Não", null).show();
                return true;
            }
        };
    }

    @OnClick(R.id.btSalvar)
    public void onClick() {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        Log.d("LogX", "Passou na validação");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(etNome.getText().toString());
        pessoa.setEmail(etEmail.getText().toString());
        pessoa.setNumeroCelular(Long.parseLong(etFone.getText().toString()));

        new PessoaDao().salva(pessoa);

        atualizaDadosLista();
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
