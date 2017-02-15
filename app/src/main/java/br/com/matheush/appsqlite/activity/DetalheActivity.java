package br.com.matheush.appsqlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.com.matheush.appsqlite.R;
import br.com.matheush.appsqlite.dao.PessoaDao;
import br.com.matheush.appsqlite.model.Pessoa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheActivity extends AppCompatActivity {

    @BindView(R.id.detalhe_tv_nome)
    TextView tvNome;
    @BindView(R.id.detalhe_tv_num_telefone)
    TextView tvNumTelefone;
    @BindView(R.id.detalhe_tv_email)
    TextView tvEmail;

    long index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        index = intent.getLongExtra("index", 0);

        Pessoa pessoa = new PessoaDao().getObejeto(index);

        tvNome.setText(pessoa.getNome());
        tvNumTelefone.setText(String.valueOf(pessoa.getNumeroCelular()));
        tvEmail.setText(pessoa.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_editar_pessoa:
                Intent intent = new Intent(DetalheActivity.this, EditaActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
