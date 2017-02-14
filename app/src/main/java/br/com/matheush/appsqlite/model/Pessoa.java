package br.com.matheush.appsqlite.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.x;

/**
 * Created by matheush on 08/02/17.
 */

@Table(name = "Pessoa", id = "id")
public class Pessoa extends Model {
    @Column(name = "pessoa_nome")
    private String nome;
    @Column(name = "pessoa_numero_celular")
    private long numeroCelular;
    @Column(name = "pessoa_email")
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, long numeroCelular, String email) {
        super();
        this.nome = nome;
        this.numeroCelular = numeroCelular;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pessoa pessoa = (Pessoa) o;

        if (numeroCelular != pessoa.numeroCelular) return false;
        if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
        return email != null ? email.equals(pessoa.email) : pessoa.email == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (int) (numeroCelular ^ (numeroCelular >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", numeroCelular=" + numeroCelular +
                ", email='" + email + '\'' +
                '}';
    }
}
