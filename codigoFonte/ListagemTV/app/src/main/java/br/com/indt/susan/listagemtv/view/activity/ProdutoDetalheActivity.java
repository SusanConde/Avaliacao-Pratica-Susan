package br.com.indt.susan.listagemtv.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.indt.susan.listagemtv.R;
import br.com.indt.susan.listagemtv.model.bean.Produto;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by susan on 23/03/17.
 */

public class ProdutoDetalheActivity extends BaseActivity {

    private Produto produto = new Produto();

    @BindView(R.id.imv_prod_detalhe)
    ImageView imageView_produto;

    @BindView(R.id.tv_valor)
    TextView textView_valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);
        ButterKnife.bind(this);
        setUpToolbar();
        produto = (Produto) getIntent().getSerializableExtra("produto");
        //getSupportActionBar().setTitle(produto.getNome().substring(0,produto.getNome().indexOf("\"")));
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(getContext()).load(produto.getUrlFoto()).into(imageView_produto);
        textView_valor.setText(String.valueOf("R$"+produto.getPreco()));
    }
}
