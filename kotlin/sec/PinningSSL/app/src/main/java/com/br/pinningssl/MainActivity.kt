package com.br.pinningssl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * https://medium.com/@anandgaur22/ssl-pinning-in-android-14851dc41703
 * Resumo
 *
 * CA - Certificate Authority
 * https://www.ssl.com/article/what-is-a-certificate-authority-ca/
 * https://en.wikipedia.org/wiki/Certificate_authority
 *
 * O que é pinning ?
 *
 * Um mecanismo que melhora a seguranca de um serviço ou site
 * que depende de um certificado SSL.
 *
 * Pinning permite associar um host com sua respectiva IDENTIFICADE
 * CRIPTOGRAFICA ESPERADA que deve ser aceitar pelo usuario
 *
 * Uma vez que essa identidade criptográfica é conhecida pelo host
 * , ela e associada ou "PINNER/Fixada(Pinada)" ao host.
 * Se mais de uma identidade é aceita pelo host, entao o programa
 * mantém essas identidades como um conjunto de pin ou pinset.
 *
 * Resumindo, uma conexao SSL diz ao Cliente para estabelecer uma conexão
 * encriptada com qualquer identidade que de correspondencia com o host.
 *
 * Pinning/Pinagem diz ao cliente uma identidade especifica para aceitar
 * quando estabelecer uma conexao segura
 *
 * O que é SSL (Secure Socket Layer) pinning ?
 *
 * Pinning é o processo de associar um host a um certificado ou chave publica.
 * Em outras palavras, pinning é configurar o app para rejeitar requisições que nao
 * sao uma das chaves ou certificados pre definidos. Sempre que o app conectar ao server
 * , ele compara o certificado do server com o certificado pinado/fixado ou chave publica
 * Se forem iguais, o app confia no server e estabelece uma conexao.
 *
 * O app deve incluir um certificado digital ou uma chave publica dentro do packte do app(
 * app's bundle)
 *
 * Quando usar ?
 *
 * Quando um app tentar estabelecer uma conexao com um servidor, ele nao sabe qual certificado confiar
 * Um app nativo android ou IOS dependem dos certificados de suas respectividas empresas, Android CA da Google
 * ou iOS Trust Store
 *
 * Esse metodo possui uma fragilidade: Um hacker pode gerar um certificado assinado e inclui-lo
 * no certificado iOS/Android Trust Store ou hackear o certificado CA raiz.
 * Isso permite o atacando realizar um ataca man-in-the-middle.
 *
 *
 * Restringir o conjunto de certificados confiaveis atraves de pinning previne de atacas
 * que analisam o funcionamento do app e de como ele se comunica com o servidor.
 *
 * SSL Pinning
 *
 * - protege contra ataques man in the middle
 *  - um atacante pode colocar o seu proprio certificado SSL no seu app
 *  - SSL Pinning previni isso garantindo que somente um conjunto predefinido de certificados
 *  e confiavel
 *
 * - Agrega resiliencia contra ataques que comprometam a CA (Certificate Authority)
 *  - Se uma chave privada de uma CA confiavel for comprometida, atacantes
 *  podem emitir certificados fradulentos que um app poderia confirar por desconhecer
 *  a fraude. SSL pinning eliia esse risco desde que o app nao seja somente dependente
 *  de uma CA
 *
 *  - Empodera a privacidade
 *      - Isso é possivel através da restrição de acesso nao autorizado a informaçoes sensiveis
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            // val url = "https://medium.com/@anandgaur22/ssl-pinning-in-android-14851dc41703"
            with(URL("https://wikipedia.org").openConnection()) {
                if (Log.isLoggable("HTTP_LOG", Log.INFO)) {
                    Log.i("HTTP_LOG", "Url: $url")
                    Log.i(
                        "HTTP_LOG", "Content: ${
                            BufferedReader(
                                InputStreamReader(getInputStream())
                            ).readLines()
                        }"
                    )
                }
            }
        } catch (e: Throwable) {
            if (Log.isLoggable("HTTP_LOG", Log.ERROR)) {
                Log.e("HTTP_LOG", "Exception: ${e.message}")
            }
        }
    }
}