package modular.xplore.com.br.accessotherapps.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import modular.xplore.com.br.accessotherapps.BuildConfig;

/**
 * Created by r028367 on 05/02/2018.
 *
 * Ideia interessantes baseadas nos seguintes posts
 *
 * https://stackoverflow.com/questions/34309564/how-to-get-app-market-version-information-from-google-play-store
 * https://stackoverflow.com/questions/11753000/how-to-open-the-google-play-store-directly-from-my-android-application
 *
 */

public class BuildUtils {


    public static boolean openGooglePlayWithMarketBaseUrl(Activity activity, String marketBaseUrl) {
        return openGooglePlayMarket(activity, Uri.parse(marketBaseUrl));
    }


    public static boolean openGooglePlayWithBaseUrl(Activity activity, String baseUrl) {
        return openGooglePlayMarket(activity, Uri.parse(baseUrl));
    }

    private static boolean openGooglePlayMarket(Activity activity, Uri uri) {
        boolean success = true;
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (android.content.ActivityNotFoundException e) {
            Log.e("NOT_FOUND_EXCP", e.getMessage());
            success = false;
        }
        finally {
            return success;
        }
    }

    public static void tryOpenGooglePlay(Activity activity, String baseUrl, String marketBaseUrl) {
        Intent openWithBaseUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl));
        List<ResolveInfo> appsWithBaseUrl = activity.getPackageManager()
                .queryIntentActivities(openWithBaseUrl, 0);
        boolean test = false;
        for(ResolveInfo r : appsWithBaseUrl) {
            if(verifyResolveInfo(r, openWithBaseUrl)) {
                activity.startActivity(openWithBaseUrl);
                test = true;
                break;
            }
        }

        if(!test) {
            Intent openWithMarketBaseUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(marketBaseUrl));
            List<ResolveInfo> appsWithMarketBaseUrl = activity.getPackageManager()
                    .queryIntentActivities(openWithMarketBaseUrl, 0);
            for(ResolveInfo r : appsWithMarketBaseUrl) {
                if(verifyResolveInfo(r, openWithMarketBaseUrl)) {
                    activity.startActivity(openWithMarketBaseUrl);
                    test = true;
                    break;
                }
            }
        }

        if(!test) {
            openGooglePlayWithMarketBaseUrl(activity, marketBaseUrl);
        }
    }

    private static boolean verifyResolveInfo(ResolveInfo r, Intent intent) {
        if(r.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
            ActivityInfo activityInfo = r.activityInfo;
            ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName
                    , activityInfo.name);

            // Garante que a activity do google play nao vai abrir na mesma pilha que a activity do seu App
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //
            intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            // Se a activity que se quer chamar ja estiver na pilha, ela sera
            // colocada no topo da pilha sem que ela seja criada como uma nova instancia
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setComponent(componentName);
            return true;
        }
        return false;
    }

    public static String getVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return  packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            Log.e("GET_VERSION_EXCP", e.getMessage());
        }
        return null;
    }

    public static String getVersion() {
        return  BuildConfig.VERSION_NAME;
    }

    public static String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }


    public static void requestVersionCodeGooglePlay(AsyncRequestGooglePlayStore.Callback callback, String baseUrl) {
        AsyncRequestGooglePlayStore asyncRequestGooglePlayStore = new AsyncRequestGooglePlayStore(callback, baseUrl);
        asyncRequestGooglePlayStore.execute();
    }


    public static class AsyncRequestGooglePlayStore extends AsyncTask<Void, Void, String> {

        public interface Callback {
            void execute(String version, String response);
        }

        private final Callback callback;
        private final String baseUrl;

        private AsyncRequestGooglePlayStore(Callback callback, String baseUrl) {
            this.callback = callback;
            this.baseUrl = baseUrl;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String clear = null;
            try {
                URL url = new URL(baseUrl);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)  url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                String e = "", in;
                StringBuilder logging = new StringBuilder();
                while ( (in = bufferedReader.readLine()) != null) {
                    logging.append(in);
                    if(in.matches(">*.\\s(\\d\\.){1,2}<")) {
                        e = in;
                        break;
                    }
                }
                if(!e.equals("")) {
                    clear = e.replace("(?i)<td[^>]*>", "").trim();
                    Log.i("CLEAR_INFO", clear);
                }
            }
            catch (IOException e) {
                Log.e("GOOGLE_PLAY_STORE", e.getMessage());
            }
            return clear;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callback.execute(getVersion(), s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            callback.execute(getVersion(), s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
