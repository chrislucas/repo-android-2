package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    Capitulo 8 livro Android 2d Graphics

    Nesse capitulo é apresentado os tipos de "Operacoes de transformacao" de coordenadas
    - Translation/move
    - Scaling
    - Rotation

    Porém nesse capítulo só sera abordado as 2 primeiras operacoes

    Essas operacoes podem ser aplicadas sequencialmente para obter transformacoes
    complexas

    - Translation
        - É a operacao que move a ORIGEM do sistema de coordenadas, horizontalmente
        ou verticalmente, uma quantidade de valor especifico

        fun translation(moveX, moveY)
            newX = moveX + x // ou x += moveX
            newY = moveY + y // ou y += moveY
    - Scaling
        - Operacao que permite esticar ou encolher coordenadas ao longo
        do eixo X ou Y
        fun scaling(scaleX, scaleY)
            newX = scaleX + x // ou x += scaleX
            newY = scaleY + y // ou y += scaleY

    Relembrado os tipos de coordenadas e suas origens

    - Device coordinates
        - tem a origem (0, 0) no canto superior esquerdo
        - o eixo X se estende da esquerda para direita (0,0 a 0, n)
        - o eixo Y se estende de cima para baixo (0, 0 a n, 0)
    - Logical coordinates
        - tem a origem (0, 0)no canto inferior esquerdo
        - eixo X se estende da esqurda pra direita
        - eixo Y se estende de baixo para cima

 8.2 - Exemplo desenhando um triangulo na coordenada logica
     - Definir um sistema coordenada logico para o triangulo.
     - Usar uma razao ou percentaul de largura e altura para CustomView'
        - Esse tipo de sistema de cordenada e chamado de normalized coordinate system
        - Como exemplo do livro saimos de uma coordenada de dispositivo (Device coordinate)
        para uma coordenada lógica (Logical coordinate)
        - Ela tem dimensao 1280x800 se aplicarmos a divisao chegamos numa razao de 1.6
          - 1280/800 = 1.6
          - Dessa forma na coordenada logica teremos no ponto mais extremo (direita superior)
          os pontos 1.6 e 1.0


     - FUNCOES DE DESENHO DA API CANVAS
        - por padrao, todas sa funcoes usam coordenadas de dispositivo, entao precisamos
        converter coordenadas logicas para de dispositovo.
        - Exemplo de como fazer isso
            - Partindo do exemplo anterior numa tela de 1280x800
            - A coordenada logica nos leva para uma tela de 1.6/1.0
            - aplicamos primeiro a operacao scale(scaleX, scaleY) depois a
            operacao move(moveX, moveY)
                1 - Scaling
                  - Os quatro cantos da coordenada logica
                  - A(0, 0), B(0, 1), C(1.6, 1), D(1.6, 0)
                  - A operacao Scale scale(800, -800)

                    - Por que 800 em X ?
                        - 1280/1.6 = 800 a divisao inversa (1280/800) nos deu a razao 1.6/1.0
                    - Por que -800 em Y ?
                        - O eixo Y da coordenda logica parte de baixo para cima e na
                        coordenada de dispositivo é o contrário, isso implica que
                        se quisermos desenhar algo mais para cima na coordenda logica
                        aumentamos o Y e mais para baixo diminuimos o T, porém na coordenda
                        de dispositivo é o exato contrário. Dessa forma o -800 permite
                        que  façamos uma inversao do eixo Y que apontava para cima, depois
                        aplicaremos a operacao de move para que a origem va para o ponto (0, 0)

                        - Essa inversao do eixo Y ocorre porque criamos 2 pontos "fora da tela"
                            - sao eles (0, -880) e o (1280, -800)
                            - Qualquer coisa que for desenhada acima do com Y < 0 nao vai ser vista
                            - por isso precisamos aplicar a operacao move que vai deslocar o ponto
                            de origem transformando
                                - (0, -800) em (0, 0) e o
                                - (1280, -800) em (1280, 800)
                            - Isso ocorre somando (0, 800) com a operacao move

                 - resultado A(0, 0), B(0, -800), C(1280, -800), D(1280, 0)
                    - lembrar que scaling multiplca x * scaleX e y * scaleY
               2 - Moving
                 - Mover a origem com move(0, 800)
                 - lembrar que move soma x + moveX e y + moveY
                 - resultado, (0, 800), (0, 0), (), ()

 */
class CoordinateTransformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coordinate_transformation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

