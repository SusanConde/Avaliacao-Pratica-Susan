package br.com.indt.susan.listagemtv.view.activity;

import android.support.v7.widget.Toolbar;

import br.com.indt.susan.listagemtv.R;

/**
 * Created by susan on 23/03/17.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

}