package br.com.xplorer.utilsgeolocation.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import br.com.xplorer.packcustomviews.UtilsAlertDialog;
import br.com.xplorer.utilsgeolocation.callback.DefaultLocationCallbackImpl;
import br.com.xplorer.utilsgeolocation.callback.OnCompleteTaskGoogleApiFusedLocation;
import br.com.xplorer.utilsgeolocation.callback.model.DefaultMessage;
import br.com.xplorer.utilsgeolocation.callback.googleapiclient.DefaultGACConnectionCallbacks;
import br.com.xplorer.utilsgeolocation.callback.googleapiclient.DefaultGACOnConnectionFailedListener;
import br.com.xplorer.utilsgeolocation.impl.LocationSourceImpl;

/**
 * Created by r028367 on 16/03/2018.
 */

public class Geolocation {


    private static final String PERMISSIONS_LOCATION [] = {
         Manifest.permission.ACCESS_FINE_LOCATION
        , Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static final int REQUEST_ENABLE_GPS = 0x13;
    public static final int REQUEST_PERMISSION_ACCESS_LOCATION = 0x14;

    private Activity activity;
    private UtilsAlertDialog utilsAlertDialog;
    private GoogleApiClient.ConnectionCallbacks connectionCallbacks;
    private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingRequest;

    private GoogleApiClient googleApiClient;
    private ConfigLocationRequest configLocationRequest;

    public Geolocation(Activity activity) {
        init(activity);
    }

    public Geolocation(Activity activity, ConfigLocationRequest configLocationRequest) {
        init(activity);
        this.configLocationRequest = configLocationRequest;
    }

    public Geolocation(Activity activity, ConfigLocationRequest configLocationRequest
            , GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        init(activity);
        this.configLocationRequest = configLocationRequest;
    }

    public LocationRequest getLocationRequest() {
        return mLocationRequest;
    }

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return fusedLocationProviderClient;
    }

    private void init(Activity activity) {
        this.activity = activity;
        utilsAlertDialog = new UtilsAlertDialog(activity);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        configureLocationSettingsRequest();
    }

    private void createNewGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                .addOnConnectionFailedListener(onConnectionFailedListener)
                .build();
        googleApiClient.connect();
    }


    private void configureLocationSettingsRequest() {
        /***
         * Configurando as condições para requisitar a última geo posição do aparelho.
         * Com essas configurações delegamos a api de Localizacao que escolha a melhor forma
         * de utlizar o sinal dos Satelites do GPS e a bateria do celular para pegar a ultima posicao
         * registrada do aparelho
         * */

        mLocationRequest = LocationRequest.create();
        if (configLocationRequest == null) {
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setInterval(ConfigLocationRequest.DEFAULT_INTERVAL_SET_LOCATION);
            mLocationRequest.setFastestInterval(ConfigLocationRequest.DEFAULT_FAST_INTERVAL_SET_LOCATION);
        }

        else {
            mLocationRequest.setPriority(configLocationRequest.getPriority());
            mLocationRequest.setInterval(configLocationRequest.getInterval());
            mLocationRequest.setFastestInterval(configLocationRequest.getFastInterval());
        }

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        builder.setAlwaysShow(true);
        /**
         * @deprecated
         * LocationServices.SettingsApi
         * https://developers.google.com/android/reference/com/google/android/gms/location/SettingsApi
         * setPendingResult(activity, googleApiClient, builder.build());
         *
         * Usar agora SettingsClient
         * https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
         * */

        mLocationSettingRequest = builder.build();
    }

    private void enableGPSServiceLocation() {
        /**
         * Criando uma instancia da Api Client para capturar a localizacao via Google Service
         * */
        createNewGoogleApiClient();
        configureLocationSettingsRequest();
        setTask();
    }


    private void enableGPSServiceLocation(GoogleApiClient.ConnectionCallbacks connectionCallbacks
            , GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        /**
         * Criando uma instancia da Api Client para capturar a localizacao via Google Service
         * */
        this.connectionCallbacks = connectionCallbacks;
        this.onConnectionFailedListener = onConnectionFailedListener;
        createNewGoogleApiClient();
        configureLocationSettingsRequest();
        setTask();
    }

    private OnCompleteListener<LocationSettingsResponse> locationSettingsResponseOnCompleteListener
            = new OnCompleteListener<LocationSettingsResponse>() {
        @Override
        public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
            try {
                LocationSettingsResponse locationSettingsResponse = task.getResult(ApiException.class);
                LocationSettingsStates locationSettingsStates = locationSettingsResponse.getLocationSettingsStates();
                String message = String.format("Is GPS Present ? %s\n Is GPS Usable ? %s\n Is Location Present ? %s\n Is Location usable ? %s\n"
                        , locationSettingsStates.isGpsPresent()
                        , locationSettingsStates.isGpsUsable()
                        , locationSettingsStates.isLocationPresent()
                        , locationSettingsStates.isLocationUsable()
                );
                Log.i("ON_COMP_RESPONSE_SET", message);
            } catch (ApiException e) {
                String message = e.getMessage() == null ? "Não foi possíve capturar o erro" : e.getMessage();
                Log.e("EXCP_GOOGLE_API", message);
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(activity, REQUEST_ENABLE_GPS);
                        } catch (IntentSender.SendIntentException sendIntentExcp) {
                            message = sendIntentExcp.getMessage() == null
                                    ? "Não foi possivel capturar o erro" : sendIntentExcp.getMessage();
                            Log.e("SEND_INTENT_EXCP", message);
                        }
                        break;
                    case LocationSettingsStatusCodes.CANCELED:
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        }
    };

    private OnCompleteListener<LocationSettingsResult> locationSettingsResultOnCompleteListener =
            new OnCompleteListener<LocationSettingsResult>() {
                @Override
                public void onComplete(@NonNull Task<LocationSettingsResult> task) {
                    Status status = task.getResult().getStatus();
                    LocationSettingsResult locationSettingsResult = new LocationSettingsResult(status);
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            break;
                        case LocationSettingsStatusCodes.CANCELED:
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(activity, REQUEST_ENABLE_GPS);
                            } catch (IntentSender.SendIntentException e) {
                                String msg = e.getMessage() == null ? "Não foi possível recuperar a mensagem de erro"
                                        : e.getMessage();
                                Log.e("SEND_INTENT_EXCP", msg);
                            }
                            break;
                    }
                }
            };

    private void setTask() {
        Task<LocationSettingsResponse> taskResponse = LocationServices
                .getSettingsClient(activity).checkLocationSettings(mLocationSettingRequest);
        taskResponse.addOnCompleteListener(activity, locationSettingsResponseOnCompleteListener);
    }

    /**
     * Esse metodo utiliza recursos obsoletos da API
     * */
    private void setPendingResult(final Activity activity
            , GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest) {
        PendingResult<LocationSettingsResult> pendingResult = LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, locationSettingsRequest);
        pendingResult.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.CANCELED:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(activity, REQUEST_ENABLE_GPS);
                        } catch (IntentSender.SendIntentException e) {
                            String msg = e.getMessage() == null ? "Não foi possível recuperar a mensagem de erro"
                                    : e.getMessage();
                            Log.e("SEND_INTENT_EXCP", msg);
                        }
                        break;
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    public void getLastKnowLocation(final OnCompleteTaskGoogleApiFusedLocation onCompleteTaskGoogleApiFusedLocation) {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.i("TEST_GPS_PROVIDER"
                    , String.valueOf(LocationSourceImpl.testGPSProviderIsEnabled(activity)));
            /**
             * Pegar uma instancia de Task<Location> e usar um listener para capturar a localizacao
             * quando a API estiver com a informacao pronta.
             * */
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        if (location != null) {
                            onCompleteTaskGoogleApiFusedLocation.successGetLocation(location);
                        }
                        else {
                            /**
                             * A instancia de Location pode ser nula em 2 situações
                             *
                             * 1) Se a opção de locailizacao estiver desativada. Quando desativamos
                             * a localização a último valor registrado é removido do cache, então não
                             * é possível recuparar nem mesmo a última localização.
                             *
                             * 2) O Google pay service pode ter sido reiniciado e não temos um cliente
                             * providor de localização ativo. Segundo a documentacao, para contornar essa situacao
                             * podemos criar uma nova instancia de {@link GoogleApiClient} e solicitar novamente
                             * uma atualização da posicao
                             * */
                            DefaultMessage defaultMessage = new DefaultMessage("Não temos em cache a sua última posição." +
                                    "\nAguarde enquanto fazemos uma nova requisição de atualização.");
                            enableGpsWithGoogleApiClient();
                        }

                    }
                    else {
                        DefaultMessage defaultMessage = new DefaultMessage("Não foi possível capturar a sua localização.");
                        onCompleteTaskGoogleApiFusedLocation.failureGetLocation(defaultMessage);
                        enableGpsWithGoogleApiClient();
                    }
                }
            });
        } else {
            requestPermissions();
        }
    }


    private boolean shouldShowRequestPermissionRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    private boolean checkSelfPermission(String permission) {
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermissions() {
        boolean shouldI = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (shouldI) {
            /**
             * Abrir uma AlertDialog
             * */
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.dismiss();
                            break;
                        case DialogInterface.BUTTON_POSITIVE:
                            request();
                            break;
                    }
                }
            };
            utilsAlertDialog.buildDefault(false
                    , "Permissão para acessar os dados de geolocalizacao"
                    , "Para o bom funcionamento do App permita o acesso");
            utilsAlertDialog.setPositiveButton("Sim", onClickListener);
            utilsAlertDialog.setNegativeButton("Não", onClickListener);
            try {
                utilsAlertDialog.safeShowing();
            } catch (Exception e) {
                Log.e("EXCP_REQ_PERMISSION_LOC", e.getMessage());
            }
        } else {
            request();
        }
    }

    private void request() {
        ActivityCompat.requestPermissions(activity, PERMISSIONS_LOCATION, REQUEST_PERMISSION_ACCESS_LOCATION);
    }


    public static void oldMethodGetLocation(Context context) {
        LocationManager locationManager = LocationSourceImpl.getLocationManager(context);
        if (locationManager != null) {
        }
    }

    public void enableGpsWithGoogleApiClient() {
        connectionCallbacks = new DefaultGACConnectionCallbacks();
        onConnectionFailedListener = new DefaultGACOnConnectionFailedListener();
        enableGPSServiceLocation();
    }

    /**
     * Antes de solicitar atualizações da localizacao devemos configurar o serviço de requisição de localizacao
     * para que a API de localizacao possa escolher a melhor estratégia para recuperar a ultima localizacao
     * através do sinal dos satelites do sistema de GPS.
     *
     * Isso é feito no método
     * {@link Geolocation#enableGPSServiceLocation(GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener)}
     * onde criamos uma instancia de {@link GoogleApiClient} e configuramos a estratégia de Localizacaoc riando
     * uma instancia de {@link LocationRequest#create()}
     * */

    public void startServiceUpdateLocation(PendingIntent pendingIntent) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        } else
            fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, pendingIntent);
    }

    public void removeLocationUpdates(PendingIntent pendingIntent) {
        fusedLocationProviderClient.removeLocationUpdates(pendingIntent);
    }


    public void removeLocationUpdates(DefaultLocationCallbackImpl defaultLocationCallback) {
        fusedLocationProviderClient.removeLocationUpdates(defaultLocationCallback).addOnCompleteListener(activity
                ,
            new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            }
        );
    }

    public void startServiceUpdateLocation(final DefaultLocationCallbackImpl defaultLocationCallback, final Looper looper) {
        OnSuccessListener<LocationSettingsResponse> onSuccessListener =  new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions();
                }
                else {
                    fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, defaultLocationCallback, looper);
                }
            }
        };

        OnFailureListener onFailureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int statusCode = ((ApiException) e).getStatusCode();
                switch (statusCode) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            resolvableApiException.startResolutionForResult(activity, REQUEST_ENABLE_GPS);
                        } catch (IntentSender.SendIntentException isExcp) {
                            String message = isExcp.getMessage() == null ? "Não foi possível capturar o erro" : isExcp.getMessage();
                            Log.e("EXCP_UPDATE_LOC_FAIL", message);
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        defaultLocationCallback.onLocationResult(null);
                        break;
                }
            }
        };
        SettingsClient settingsClient = LocationServices.getSettingsClient(activity);
        settingsClient.checkLocationSettings(mLocationSettingRequest)
                .addOnSuccessListener(activity, onSuccessListener)
                .addOnFailureListener(activity, onFailureListener);
    }

}
