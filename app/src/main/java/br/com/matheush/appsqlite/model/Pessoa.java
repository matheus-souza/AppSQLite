package br.com.matheush.appsqlite.model;

/**
 * Created by matheush on 08/02/17.
 */

public class Pessoa {
    private long id;
    private String nome;
    private long numeroCelular;
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, long numeroCelular, String email) {
        this.nome = nome;
        this.numeroCelular = numeroCelular;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

        Pessoa pessoa = (Pessoa) o;

        if (id != pessoa.id) return false;
        if (numeroCelular != pessoa.numeroCelular) return false;
        if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
        return email != null ? email.equals(pessoa.email) : pessoa.email == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (int) (numeroCelular ^ (numeroCelular >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroCelular=" + numeroCelular +
                ", email='" + email + '\'' +
                '}';
    }
}
