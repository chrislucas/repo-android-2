package br.com.xplorer.bindservicewithmessegerclass.bindservice;

import android.app.Service;
import android.os.Binder;

/**
 * Created by r028367 on 29/03/2018.
 */

public class BindService extends Binder {

    private Service service;

    public BindService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    @Override
    public void linkToDeath(DeathRecipient recipient, int flags) {
        super.linkToDeath(recipient, flags);

    }

    @Override
    public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
        return super.unlinkToDeath(recipient, flags);
    }
}
