package modular.xplore.com.br.accessotherapps.entities;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by r028367 on 06/03/2018.
 */

public class App {

    private ImageView icon;
    private String idApplicationOnGooglePlay, action, category;

    public App(ImageView icon, String idApplicationOnGooglePlay, String action, String category) {
        this.icon = icon;
        this.idApplicationOnGooglePlay = idApplicationOnGooglePlay;
        this.action = action;
        this.category = category;
    }
}
