package br.com.indt.susan.listagemtv.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.indt.susan.listagemtv.R;
import br.com.indt.susan.listagemtv.presenter.ProdutoPresenter;
import butterknife.ButterKnife;

public class ProdutosActivity extends BaseActivity {

    RecyclerView recyclerView_produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        ButterKnife.bind(this);
        setUpToolbar();
        getSupportActionBar().setTitle(R.string.app_name);
        ProdutoPresenter produtoPresenter = new ProdutoPresenter(this);
        setRecyclerView();
        produtoPresenter.carregarProdutosURL(recyclerView_produtos);

    }


    public void setRecyclerView() {

        //Aqui é instanciado o Recyclerview
        recyclerView_produtos = (RecyclerView) findViewById(R.id.recycler_produtos);
        recyclerView_produtos.setLayoutManager(new LinearLayoutManager(this));
    }

}