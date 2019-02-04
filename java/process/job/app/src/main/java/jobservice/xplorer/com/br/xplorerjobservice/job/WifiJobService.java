package jobservice.xplorer.com.br.xplorerjobservice.job;


import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class WifiJobService extends JobService {

    public WifiJobService() {}


    public static final int ID = 0x33;

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public static JobInfo createJobWithWifiDependency(Context context) {
        ComponentName componentName = new ComponentName(context, WifiJobService.class.getName());
        JobInfo.Builder builderJobInfo = new JobInfo.Builder(ID, componentName);

        NetworkRequest.Builder builderNetworkRequest = new NetworkRequest.Builder();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            builderNetworkRequest.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            builderNetworkRequest.addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_CONGESTED);
            builderJobInfo
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .setRequiredNetwork(builderNetworkRequest.build());
        }

        return builderJobInfo.build();
    }
}
