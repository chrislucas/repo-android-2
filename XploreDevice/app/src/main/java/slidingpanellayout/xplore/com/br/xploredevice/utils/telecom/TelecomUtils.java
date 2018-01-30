package slidingpanellayout.xplore.com.br.xploredevice.utils.telecom;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;

/**
 * Created by r028367 on 30/01/2018.
 */

public class TelecomUtils {

    public static TelecomManager getTelecomManager(Context context) {
        return  (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
    }

}
