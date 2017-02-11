package br.com.matheush.appsqlite;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by matheush on 11/02/17.
 */

public class MyApplication extends Application {
    public static final String MSG_VAZIO = "Favor preencher";
    public static final String MSG_INVALIDO = "Favor digitar um valor válido";

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        ActiveAndroid.dispose();
    }
}
