package br.com.xplorer.utilsgeolocation.resultreceiver;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static br.com.xplorer.utilsgeolocation.resultreceiver.ResultReceiverCode.RESULT_FAILURE;
import static br.com.xplorer.utilsgeolocation.resultreceiver.ResultReceiverCode.RESULT_SUCCESS;

/**
 * Created by r028367 on 20/03/2018.
 */
@IntDef({RESULT_SUCCESS, RESULT_FAILURE})
@Retention(RetentionPolicy.SOURCE)
public @interface ResultReceiverCode {
    int RESULT_SUCCESS = 1;
    int RESULT_FAILURE = 2;
}
