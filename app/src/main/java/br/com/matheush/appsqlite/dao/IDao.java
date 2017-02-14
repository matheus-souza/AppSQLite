package br.com.matheush.appsqlite.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.matheush.appsqlite.model.Pessoa;

/**
 * Created by matheush on 08/02/17.
 */

public interface IDao<T> {
    public void salva(T objeto);
    public void deleta(Pessoa pessoa);
    public void detetaTodos();
    public void altera(T objeto);
    public List<T> getObejetos();
    public T getObejeto(int id);



}
