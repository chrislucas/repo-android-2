package memory.xplorer.com.managermemory;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Hashtable;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements ComponentCallbacks2 {


    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager != null)
            activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    private double toMB(long bytes) {
        return bytes * 1.0f / (1024.0 * 1024.0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Memory Info");

        String message = String.format(Locale.getDefault()
            , "Avaialable Mem: %d bytes/ %f MB.\nLow Mem: %s\nThreshold: %d\nTotal Mem: %d bytes / %f MB"
            , memoryInfo.availMem
            , toMB(memoryInfo.availMem)
            , memoryInfo.lowMemory
            , memoryInfo.threshold
            , memoryInfo.totalMem
            , toMB(memoryInfo.totalMem)
        );

        builder.setMessage(message);
        builder.create().show();
    }

    private static final Hashtable<Integer, String> COMPONENT_CALLBACK_MEMORY;

    static {
        COMPONENT_CALLBACK_MEMORY = new Hashtable<>();
        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN, "TRIM_MEMORY_UI_HIDDEN");

        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE, "TRIM_MEMORY_RUNNING_MODERATE");
        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL, "TRIM_MEMORY_RUNNING_CRITICAL");
        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW, "TRIM_MEMORY_RUNNING_LOW");

        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_BACKGROUND, "TRIM_MEMORY_BACKGROUND");
        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_MODERATE, "TRIM_MEMORY_MODERATE");
        COMPONENT_CALLBACK_MEMORY.put(ComponentCallbacks2.TRIM_MEMORY_COMPLETE, "TRIM_MEMORY_COMPLETE");
    }

    @Override
    public void onTrimMemory(int level) {
        switch (level) {
            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                /**
                 * Liberar da memoria os componentes UI uma vez que a tela nao
                 * esta sendo mostrada
                 * */
                break;
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
                /**
                 *
                 * O device está com baixa disponibilidade de memoria enquanto o App esta
                 * sendo executado.
                 *
                 * Se o evento for TRIM_MEMORY_RUNNING_CRITICAL entao o sistema comecara
                 * a matar processos em background
                 * */
                break;
            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
                /**
                 * O sistema ira liberar o tanto de memoria que ele conseguir faze-lo.
                 *
                 * O app esta na lista de cache LRU e o sistema esta rodando com baixa disponibilidade
                 * de memoria. O evento lançado (int level) indica em que posicao na lista o App
                 * está, por exemplo 'TRIM_MEMORY_COMPLETE' indica que o processo desse app
                 * sera um dos primeiros a serem parados
                 * */
                break;
        }

        if(COMPONENT_CALLBACK_MEMORY.containsKey(level)) {
            Log.i("ON_TRIM_MEMORY", COMPONENT_CALLBACK_MEMORY.get(level));
        }
    }
}
