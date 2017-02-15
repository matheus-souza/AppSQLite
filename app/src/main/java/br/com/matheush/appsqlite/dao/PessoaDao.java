package br.com.matheush.appsqlite.dao;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import br.com.matheush.appsqlite.model.Pessoa;

/**
 * Created by matheush on 08/02/17.
 */

public class PessoaDao implements IDao<Pessoa> {
    @Override
    public void salva(Pessoa pessoa) {
        pessoa.save();
    }

    @Override
    public void deleta(Pessoa pessoa) {
        pessoa.delete();
    }

    @Override
    public void detetaTodos() {
        new Delete().from(Pessoa.class).execute();
    }

    @Override
    public List<Pessoa> getObejetos() {
        return new Select().from(Pessoa.class).orderBy("id ASC").execute();
    }

    @Override
    public Pessoa getObejeto(long id) {
        return new Select().from(Pessoa.class).where("id = ?", id).executeSingle();
    }
}
