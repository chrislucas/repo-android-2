package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellSignalStrengthLte;

import java.util.List;

import slidingpanellayout.xplore.com.br.xploredevice.AsyncHttpRequest;
import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.utils.telephony.TelephonyUtils;

public class ExplorerInfoCellTowerActivity extends AppCompatActivity {

    private final TelephonyUtils.CallbackCellTowerInfo callbackCellTowerInfo
            = new TelephonyUtils.CallbackCellTowerInfo() {
        @Override
        public void callback(List<CellInfo> list) {
            if(list != null && list.size() > 0) {
                for(CellInfo cellInfo : list) {
                    if(cellInfo instanceof CellInfoLte) {
                        CellIdentityLte cellIdentityLte = ((CellInfoLte) cellInfo).getCellIdentity();
                        //CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                        int mobileCountryCode = cellIdentityLte.getMcc();
                        int mobileNetworkCode = cellIdentityLte.getMnc();
                    }
                }
            }
        }
    };

    private final AsyncHttpRequest.CallbackPostExecute<String> callbackPostExecute
            = new AsyncHttpRequest.CallbackPostExecute<String>() {
        @Override
        public void execute(String o) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_info_cell_tower);
        TelephonyUtils.explorerCellTowerInfo(this, callbackCellTowerInfo);

        AsyncHttpRequest<Void, String> asyncHttpRequest = new AsyncHttpRequest<>("", callbackPostExecute);
        asyncHttpRequest.execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case TelephonyUtils.REQUEST_CODE_ACCESS_COARSE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    TelephonyUtils.explorerCellTowerInfo(this, callbackCellTowerInfo);
                break;
        }
    }
}
