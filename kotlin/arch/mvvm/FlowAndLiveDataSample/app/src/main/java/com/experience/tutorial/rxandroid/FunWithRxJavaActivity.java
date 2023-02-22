package com.experience.tutorial.rxandroid;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.experience.tutorial.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import java.util.Locale;

/*
   Link para novo logcat
   https://developer.android.com/studio/releases#logcat
*/
public class FunWithRxJavaActivity extends AppCompatActivity {

  private ObservableOnSubscribe<String> getMockObservable(final List<String> names) {
    return emitter -> {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        for (int i = 0; i < names.size(); i++) {
          final String name = names.get(i);
          if (name.isBlank()) {
            if (name.isBlank()) {
              final String message =
                  String.format(Locale.getDefault(), "String na posicao %d está vazia", i);
              // chamada ao onError
              emitter.onError(new Exception(message));
            }
            emitter.onNext(name);
          }
        }
        emitter.onComplete();
      }
    };
  }

  private void checkCreateFun(final List<String> names) {
    /*
       create: Permite criar um observable "do zero". Assim cabe a quem estiver escrevendo
       o codigo realizar a chamada aos metodos do Observer
    */
    final ObservableOnSubscribe<String> observableOnSubscribe = getMockObservable(names);
    final Consumer<String> onNext = str -> Log.i("TEST_CREATE_OBS_JAVA", str);
    final Consumer<Throwable> onError =
        throwable -> Log.e("ON_ERROR_SUBS_JAVA", throwable.getMessage());

    Observable.create(observableOnSubscribe).subscribe(onNext, onError, () -> {}).dispose();
  }

  /*
     https://github.com/ReactiveX/RxJava/wiki/Error-Handling
     Techniques for recovering from onError notifications
     Algumas estrategias para lidar com erros emitidos por um Observable
     1 - Reter o erro e trocar para um observer backup para continuar a sequencia
  */

  private void checkObserverErrorHandler(final List<String> names) {
    final ObservableOnSubscribe<String> observableOnSubscribe = getMockObservable(names);
    final Consumer<String> onNext = str -> Log.i("TEST_CREATE_OBS_JAVA", str);
    final Consumer<Throwable> onError =
        throwable -> Log.e("ON_ERROR_SUBS_JAVA", throwable.getMessage());
    Observable.create(observableOnSubscribe)
        .doOnError(throwable -> Log.e("DO_ON_ERROR_SUBS_JAVA", throwable.getMessage()))
        .subscribe(onNext, onError, () -> {})
        .dispose();
  }

  /*
     https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators
  */

  private void checkObserverErrorHandlingOperator() {}

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fun_with_rx_java);
    checkCreateFun(List.of("a", "b", "c", "d", "", "     "));
  }
}
