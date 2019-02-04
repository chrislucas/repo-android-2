package modular.xplore.com.br.xploreannotationtypedef.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static modular.xplore.com.br.xploreannotationtypedef.constants.Seasons.FALL;
import static modular.xplore.com.br.xploreannotationtypedef.constants.Seasons.SPRING;
import static modular.xplore.com.br.xploreannotationtypedef.constants.Seasons.SUMMER;
import static modular.xplore.com.br.xploreannotationtypedef.constants.Seasons.WINTER;

/**
 * Created by r028367 on 26/02/2018.
 */
@IntDef({WINTER, SUMMER, SPRING, FALL})

/**
 * {@link Retention} - Meta-anotacao
 * https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Retention.html
 *
 * Indica quanto tempo uma anotacao deve ser mantida. Se nao for definida nenhuma
 * politica de retencao a retencao padrao eh {@link RetentionPolicy#CLASS}
 *
 *
 * Segundo a documentacao essa meta-anotacao so tem efeito se usada diretamente para anotacoes, caso
 * usada como um tipo-membro em outra anotacao ela nao tem efeito algum
 * */

@Retention(RetentionPolicy.SOURCE)
/**
 * Enum RetentionPolicy
 * https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/RetentionPolicy.html
 *
 * As constantes desse enum descrevem as politicas para manter as anotacoes. Sao usadas juntas com
 * a anotacao Retention meta-annotation para especificar quanto tempo as anotacoes devem ser mantidas
 *
 *
 *
 *  {@link RetentionPolicy#SOURCE}
 *  Anotacoes sao descartadas pelo compilador
 *
 *  {@link RetentionPolicy#CLASS}
 *  Anotacoes sao gravas no arquivo .class pelo compilador mas nao precisam ser mantidas pela VM
 *  em tempo de execucao. Esse eh o comportamento padrao
 *
 *  {@link RetentionPolicy#RUNTIME}
 *  Anotacoes sao gravadas no arquivo .class pelo compilador e sao mantidas pela VM assim podem
 *  ser lidas usando os recursos de  'reflexao
 *
 * */

public @interface Seasons {
    int WINTER  = 0x01;
    int SUMMER  = 0x02;
    int SPRING  = 0x03;
    int FALL    = 0x04;
}
