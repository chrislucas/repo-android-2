package com.br.funwithdatabinding.view.features.music.circlefifthandforth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://en.wikipedia.org/wiki/Circle_of_fifths

    Sobre ciclo das quintas e acordes
    https://youtu.be/Nb9VrGrZLyo?si=-RKf0-SfOhrwzqP2

    Notacao musical custom view
    https://github.com/nitishp/SheetMusicView

 */
class CircleOfFifthAndForthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_circle_of_fifth_and_forth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


class FifthChordWheel {
    /*
        Criar uma custom view dessa maneita no video
        https://youtube.com/shorts/Pj6MynwoUg0?si=Fo1dc4kjx9WfHkzc
        - O video acima mostra
            - Escala maiores
            - Escala menores relativas
            - A Setima diminuta
            - No centro mostra a clave, armadura de clave, pentagrama e o acorde

       - TODO
            - Criar uma view que contenha todas as funcionalidades acima
            - Adicionar a funcionalidade de modificar a clave {Sol segunda linha,
            Fa Quarta Linha, e Do terceira Linha}

        - TODO
            - Essa Classe aqui Vai juntar as seguintes classes
                - Ciclo das escalas maiores
                - Ciclo das escalas menores
                - Ciclo das setimas deminutas
                - a view que bloqueia formando uma view parecida com o instrumento:
                    Weedstock Ciclo de Quintas Maderira
            - Os circulos devem ser cocentricos do maior para o menor
            - Deve ser possivel inverter os circulos de ordem
                - Padrao deve ser Escalas Maiores, Menores e Diminutas
                - Mas poderiamos mostrar de tras parafrente


     */
}


class MajorFifthCircleView {
    /*
           TODO
            - Criar o cinculo das escalas maiores
     */
}

class RelativeMinorCircleView {
    /*
           TODO
            - Criar o cinculo das escalas menores
     */
}

class DiminishedSeventhCircleView {
    /*
        TODO
           - Criar o ciruclo dos acordes de setima diminuto
     */
}


class BlockViewCircle {
    /*
         https://youtu.be/qV0of9kUYHs?si=aUMRD5ZluROHoKFW
        TODO
         - Criar uma view que bloqueia as escalas mostrando somente a TONICA, sua anterior e sucessora
         e a sétima como no video acima
         -
     */
}