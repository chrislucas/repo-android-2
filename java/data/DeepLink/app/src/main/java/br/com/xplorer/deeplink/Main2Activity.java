package br.com.xplorer.deeplink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {


    /**
     * https://developer.android.com/training/app-links/
     *
     * DeepLinks: Sao URLs que permitem que o usuario acesse um determinado conteudo
     * dentrp do app. Podemos adicionar deep links atraves de intent-filters e extrair
     * dados a partir de 'incomming intents' para direcionar o usuario a activity correta
     *
     * Se outros Apps souberem como lidar com um intent-filter sera exibida uma tela para
     * o usuario decidir qual app ira atender a solicitacao.
     *
     *
     * Android App Links: A partir do android 6.0 (API 23) >. Permite definir que um app
     * defina a si mesmo como um manipulador padrao de um dado tipo de link. Se o usuario
     * nao quiser que tal app seja o manipulador padrao ele pode sobreescrever tal comportamento
     * a partir das configuracoes do dispositivo.
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
