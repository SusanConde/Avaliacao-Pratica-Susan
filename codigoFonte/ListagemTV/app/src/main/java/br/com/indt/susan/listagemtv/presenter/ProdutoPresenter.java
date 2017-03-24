package br.com.indt.susan.listagemtv.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.indt.susan.listagemtv.model.bean.Produto;
import br.com.indt.susan.listagemtv.model.task.AsynsProdutoHttpClient;
import br.com.indt.susan.listagemtv.util.Urls;
import br.com.indt.susan.listagemtv.view.activity.ProdutoDetalheActivity;
import br.com.indt.susan.listagemtv.view.adpater.RecyclerAdapterProduto;
import cz.msebera.android.httpclient.Header;

/**
 * Created by susan on 23/03/17.
 */

public class ProdutoPresenter{

    private Context context;

    public ProdutoPresenter(Context context) {
        this.context = context;
    }


    private List<Produto> listaProdutos = new ArrayList();

    private Produto produto;

    private Double valor = null;

    /**
     * Pegar Produtos via Json de uma URL e adicionar na lista
     */
    public void carregarProdutosURL(final RecyclerView recyclerView_produtos) {

        new AsynsProdutoHttpClient().get(Urls.LISTAGEM_TV, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("response", "" + response.toString());
                try {
                    //pegar lista de produtos
                    JSONArray jsonElements = response.getJSONArray("products");

                    for (int i = 0; i < jsonElements.length(); i++) {
                        //pegar um produto da lista
                        JSONObject produtoJson = jsonElements.getJSONObject(i);

                        //pegar a imagem pequena
                        JSONArray imagesArrayJson = produtoJson.getJSONArray("images");
                        JSONObject imagemJson = imagesArrayJson.getJSONObject(0);

                        produto = new Produto();
                        produto.setId(produtoJson.getLong("id"));
                        produto.setNome(produtoJson.getString("name"));
                        produto.setUrlFoto(imagemJson.getString("small"));
                        listaProdutos.add(produto);
                    }

                    for (int i = 0; i < listaProdutos.size(); i++){
                        carregarPreco(listaProdutos.get(i).getId(),i);
                    }

                    recyclerView_produtos.setAdapter(new RecyclerAdapterProduto(context, listaProdutos, onClickProduto()));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("erro", "" + e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

        });
    }


    public void carregarPreco(Long id, final int idx) {


        new AsynsProdutoHttpClient().get(Urls.TV_DETALHE_PARTE1 + id + Urls.TV_DETALHE_PARTE2, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("response", "" + response.toString());
                try {
                    //pegar lista de produtos
                    JSONObject jsonObject = response.getJSONObject("offer");
                    jsonObject = jsonObject.getJSONObject("result");
                    valor = jsonObject.getJSONArray("offers").getJSONObject(0).getDouble("salesPrice");

                    listaProdutos.get(idx).setPreco(valor);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("erro", "" + e);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

        });
    }

    private RecyclerAdapterProduto.ProdutoOnClickListener onClickProduto() {
        return new RecyclerAdapterProduto.ProdutoOnClickListener() {
            @Override
            public void onClickProduto(View view, int idx) {
                produto = new Produto();
                produto = listaProdutos.get(idx);
                Intent intent = new Intent(context, ProdutoDetalheActivity.class);
                intent.putExtra("produto", produto);
                context.startActivity(intent);
            }
        };
    }

}
