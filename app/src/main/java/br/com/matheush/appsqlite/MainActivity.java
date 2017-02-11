package br.com.matheush.appsqlite;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.M;
import static android.provider.Contacts.SettingsColumns.KEY;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener{
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.etNome)
    private EditText etNome;
    @NotEmpty(message = MyApplication.MSG_VAZIO)
    @BindView(R.id.etFone)
    private EditText etFone;
    @Email(message = MyApplication.MSG_INVALIDO)
    @BindView(R.id.etEmail)
    private EditText etEmail;
    @BindView(R.id.btEnviar)
    private Button btEnviar;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

        /*try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("MeuApp", MODE_PRIVATE, null);

            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS cadastropessoas (nome VARCHAR, telefone INT(4), email VARCHAR)");

            //bancoDados.execSQL("DROP TABLE IF EXISTS cadastropessoas");

            //bancoDados.execSQL("INSERT INTO cadastropessoas (nome, telefone, email) values ('Nome1', 999, 'mh.matheussouza@gmail.com')");
            //bancoDados.execSQL("INSERT INTO cadastropessoas (nome, telefone, email) values ('Nome2', 999, 'mh.matheussouza@gmail.com')");
            //bancoDados.execSQL("INSERT INTO cadastropessoas (nome, telefone, email) values ('Nome3', 999, 'mh.matheussouza@gmail.com')");

            Cursor cursor = bancoDados.rawQuery("SELECT nome,telefone,email FROM cadastropessoas", null);

            cursor.moveToFirst();

            int indiceNome = 0, indiceTelefone = 0, indiceEmail = 0;

            do {
                if (cursor.isFirst()) {
                    indiceNome = cursor.getColumnIndex("nome");
                    indiceTelefone = cursor.getColumnIndex("telefone");
                    indiceEmail = cursor.getColumnIndex("email");
                }
                Log.i("LogNome", cursor.getString(indiceNome));
                Log.i("LogTelefone", String.valueOf(cursor.getInt(indiceTelefone)));
                Log.i("LogEmail", cursor.getString(indiceEmail));
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @OnClick(R.id.btEnviar)
    public void onClick() {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        Log.d("LogX", "Passou no teste");
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
