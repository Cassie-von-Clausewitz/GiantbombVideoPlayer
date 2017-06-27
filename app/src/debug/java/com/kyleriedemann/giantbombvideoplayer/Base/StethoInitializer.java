package com.kyleriedemann.giantbombvideoplayer.Base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.inkapplications.android.applicationlifecycle.Initializer;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Hooks into the Application onCreate to initialize the Stetho Library.
 *
 * @author Renee Vandervelde
 */
@Singleton
public class StethoInitializer extends Initializer {
    @Inject
    public StethoInitializer() {}

    @Override
    public void onCreate(Application application) {
        Stetho.initialize(
                Stetho.newInitializerBuilder(application)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(application).build())
                        .build());
    }
}
