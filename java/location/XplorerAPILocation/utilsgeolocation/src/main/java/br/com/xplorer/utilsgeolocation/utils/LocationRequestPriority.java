package br.com.xplorer.utilsgeolocation.utils;

import android.support.annotation.IntDef;

import com.google.android.gms.location.LocationRequest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by r028367 on 20/03/2018.
 */

@IntDef({LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        , LocationRequest.PRIORITY_HIGH_ACCURACY, LocationRequest.PRIORITY_LOW_POWER, LocationRequest.PRIORITY_NO_POWER})
@Retention(RetentionPolicy.SOURCE)
public @interface LocationRequestPriority {
}
