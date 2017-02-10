package br.com.matheush.appsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.etNome)
    EditText etNome;
    @BindView(R.id.etFone)
    EditText etFone;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.btEnviar)
    Button btEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
    }
}
