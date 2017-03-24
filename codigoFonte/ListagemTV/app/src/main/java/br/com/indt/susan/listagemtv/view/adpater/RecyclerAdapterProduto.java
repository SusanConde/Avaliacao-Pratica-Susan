package br.com.indt.susan.listagemtv.view.adpater;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.indt.susan.listagemtv.R;
import br.com.indt.susan.listagemtv.model.bean.Produto;

/**
 * Created by susan on 24/03/17.
 */

public class RecyclerAdapterProduto extends RecyclerView.Adapter<RecyclerAdapterProduto.RecyclerViewHolder> {

    private final List<Produto> produtos;
    private final Context context;
    private ProdutoOnClickListener produtoOnClickListener;

    public RecyclerAdapterProduto(Context context, List<Produto> produtos, ProdutoOnClickListener produtoOnClickListener) {
        this.produtos = produtos;
        this.context = context;
        this.produtoOnClickListener = produtoOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.produtos != null ? this.produtos.size() : 0;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_recycler_view_produtos, viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        // Atualiza a view
        Produto produto = produtos.get(position);
        holder.tvNome.setText(produto.getNome());
        holder.progress.setVisibility(View.VISIBLE);
        // Faz o download da foto e mostra o ProgressBar
        Picasso.with(context).load(produto.getUrlFoto()).fit().into(holder.img,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progress.setVisibility(View.GONE); // download ok
                    }

                    @Override
                    public void onError() {
                        holder.progress.setVisibility(View.GONE);
                    }
                });
        // Click
        if (produtoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A variável position é final
                    produtoOnClickListener.onClickProduto(holder.itemView, position);
                }
            });
        }
    }

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int idx);
    }

    // ViewHolder com as views
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNome;
        public TextView tvPreco;
        ImageView img;
        ProgressBar progress;
        CardView cardView;

        public RecyclerViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tvNome = (TextView) view.findViewById(R.id.tv_nome);

            img = (ImageView) view.findViewById(R.id.imv_prod);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view_item_list_produto);
        }
    }


}
