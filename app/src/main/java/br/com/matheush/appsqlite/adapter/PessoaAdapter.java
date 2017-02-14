package br.com.matheush.appsqlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.matheush.appsqlite.R;
import br.com.matheush.appsqlite.model.Pessoa;

/**
 * Created by matheush on 12/02/17.
 */

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {
    private Context context;
    private List<Pessoa> pessoaList;
    private OnItemClickListener onItemClicked;
    private OnItemLongClickListener onItemLongClicked;

    public PessoaAdapter(Context context, List<Pessoa> pessoaList, OnItemClickListener onItemClicked, OnItemLongClickListener onItemLongClicked) {
        this.context = context;
        this.pessoaList = pessoaList;
        this.onItemClicked = onItemClicked;
        this.onItemLongClicked = onItemLongClicked;
    }

    @Override
    public int getItemCount() {
        return this.pessoaList != null ? this.pessoaList.size() : 0;
    }

    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_row, viewGroup, false);

        PessoaViewHolder holder = new PessoaViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final PessoaViewHolder holder, final int position) {
        //Atualiza a view
        Pessoa p = pessoaList.get(position);
        Log.d("LogAdapter", "Pessoa no adapter: " + p.toString());

        holder.tvNome.setText(p.getNome());
        holder.tvEmail.setText(p.getEmail());

        //Click
        if (onItemClicked != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClicked.onItemClicked(holder.itemView, position);
                }
            });
        }

        //Long press
        if (onItemLongClicked != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClicked.onItemLongClicked(position);
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClicked(View view, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    public class PessoaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvEmail;

        public PessoaViewHolder(View view) {
            super(view);

            tvNome = (TextView) view.findViewById(R.id.card_tv_nome);
            tvEmail = (TextView) view.findViewById(R.id.card_tv_email);
        }
    }
}
