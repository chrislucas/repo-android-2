package slidingpanellayout.xplore.com.br.xploredevice.utils.telephony.callbacks;

import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import slidingpanellayout.xplore.com.br.xploredevice.utils.telephony.TelephonyUtils;

/**
 * Created by r028367 on 30/01/2018.
 */

public class LogCellTowerInfo implements TelephonyUtils.CallbackCellTowerInfo {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss", Locale.getDefault());

    @Override
    public void callback(List<CellInfo> list) {
        if (list != null) {
            Log.i("CELL_INFO", String.format("Tamanho da lista: %d.", list.size()));
            for(CellInfo cellInfo : list) {
                explorer(cellInfo);
            }
        }
    }

    private void explorer(CellInfo cellInfo) {
        if(cellInfo instanceof CellInfoCdma) {
            explorer((CellInfoCdma) cellInfo);
        }

        else if(cellInfo instanceof CellInfoGsm) {
            explorer((CellInfoGsm) cellInfo);
        }

        else if(cellInfo instanceof CellInfoWcdma) {
            explorer((CellInfoWcdma) cellInfo);
        }

        else if(cellInfo instanceof CellInfoLte) {
            explorer((CellInfoLte) cellInfo);
        }
    }

    private void explorer(CellInfoGsm cellInfoGsm) {
        CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
        String message = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message += String.format(
                    Locale.getDefault()
                    ,"GSM Absolute RF Channel Number: %d.\nBase Station Identity Code %d.\n"
                    , cellIdentityGsm.getArfcn()
                    , cellIdentityGsm.getBsic()
            );
        }
        message += String.format(Locale.getDefault()
                ,"Cell ID: %d.\n Location Area Code: %d.\nMobile Country Code: %d.\nMobile network code: %d.\n"
                , cellIdentityGsm.getCid()
                , cellIdentityGsm.getLac()
                , cellIdentityGsm.getMcc()
                , cellIdentityGsm.getMnc()
        );
        Log.i("CELL_INFO_GSM", message);

        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
        cellSignalStrengthGsm.getAsuLevel();
        cellSignalStrengthGsm.getDbm();
        cellSignalStrengthGsm.getLevel();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cellSignalStrengthGsm.getTimingAdvance();
        }

        simpleDateFormat.format(new Date(cellInfoGsm.getTimeStamp()));
    }

    private void explorer(CellInfoWcdma cellInfoWcdma) {
        CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
        String message = String.format(
            Locale.getDefault()
            ,"Cell ID: %d.\n Location Area Code: %d.\nMobile Country Code: %d.\nMobile network code: %d.\n"
            , cellIdentityWcdma.getCid()
            , cellIdentityWcdma.getLac()
            , cellIdentityWcdma.getMcc()
            , cellIdentityWcdma.getMnc()
        );
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message +=  String.format(Locale.getDefault()
                    ,"UMTS Absolute RF Channel Number: %d.\n"
                    , cellIdentityWcdma.getUarfcn());
        }
        Log.i("CELL_INFO_WCDMA", message);
        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
        simpleDateFormat.format(new Date(cellInfoWcdma.getTimeStamp()));
    }

    private void explorer(CellInfoLte cellInfoLte) {
        CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
        String message = String.format(
            Locale.getDefault()
            ,"Cell Identify %d.\nMobile Country Code: %d.\n" +
                    "Mobile Network code: %d.\nPhysical Cell ID: %d.\nTracking Area Code: %d.\n"
            , cellIdentityLte.getCi()
            , cellIdentityLte.getMcc()
            , cellIdentityLte.getMnc()
            , cellIdentityLte.getPci()
            , cellIdentityLte.getTac()
        );
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message += String.format(Locale.getDefault()
                    ,"Absolute RF Channel Number: %d.\n"
                    , cellIdentityLte.getEarfcn());
        }
        Log.i("CELL_IDENT_LTE", message);
        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
        String anotherMessage = String.format(
            Locale.getDefault()
            , "Level ASU: %d,\nDecibels/Milli-Watts %d.\nLevel: %d.\nTiming Advanced: %d.\nTiming Advanced: %s.\nTimestamp: %d.\nTimestamp %s.\n"
            , cellSignalStrengthLte.getAsuLevel()
            , cellSignalStrengthLte.getDbm()
            , cellSignalStrengthLte.getLevel()
            , cellSignalStrengthLte.getTimingAdvance()
            , simpleDateFormat.format(new Date(cellSignalStrengthLte.getTimingAdvance()))
            , cellInfoLte.getTimeStamp()
            , simpleDateFormat.format(new Date(cellInfoLte.getTimeStamp()))
        );

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            anotherMessage += String.format(
                    Locale.getDefault()
                ,"CQI: %d.\nRSRP: %d.\nRSRQ: %d.\nRSSNR: %d"
                ,cellSignalStrengthLte.getCqi()
                ,cellSignalStrengthLte.getRsrp()
                ,cellSignalStrengthLte.getRsrq()
                ,cellSignalStrengthLte.getRssnr()
            );
        }

        Log.i("CELL_SIGNAL_LTE", anotherMessage);
    }

    private void explorer(CellInfoCdma cellInfoCdma) {
        CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
        String message = String.format(Locale.getDefault()
            ,"Base Station ID: %d.\nLatitude: %d.\nLongitude: %d.\nNetwork ID: %d.\nSystem ID: %d.\n"
            , cellIdentityCdma.getBasestationId()
            , cellIdentityCdma.getLatitude()
            , cellIdentityCdma.getLongitude()
            , cellIdentityCdma.getNetworkId()
            , cellIdentityCdma.getSystemId()
        );
        Log.i("CELL_INFO_CDMA", message);
        CellSignalStrengthCdma cellSignalStrengthCdma =  cellInfoCdma.getCellSignalStrength();
        simpleDateFormat.format(new Date(cellInfoCdma.getTimeStamp()));
    }


}
