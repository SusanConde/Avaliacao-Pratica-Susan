package br.com.indt.susan.listagemtv.view.activity;

import android.os.Bundle;

import br.com.indt.susan.listagemtv.R;

/**
 * Created by susan on 23/03/17.
 */

public class ProdutoDetalheActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);
        setUpToolbar();
    }
}
