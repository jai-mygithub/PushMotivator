package braincap.pushmotivator;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Jai on 3/21/2016.
 */
public class MyApplication extends Application {
    private static final String TAG = "JT";


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        copyBundledRealmFile(this.getResources().openRawResource(R.raw.default_realm), "default_realm");
        RealmConfiguration config0 = new RealmConfiguration.Builder(this).name("default_realm").build();
        Realm.setDefaultConfiguration(config0);



    }

    private String copyBundledRealmFile(InputStream inputStream, String outFileName) {
        try {
            File file = new File(this.getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


//mRealm = Realm.getDefaultInstance();
//AssetManager assetManager = getResources().getAssets();

        /*Creating database from file
        InputStream is = null;
        try {
            is = assetManager.open("all_quotes.json");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "onCreate: File not found");
        }

        mRealm.beginTransaction();
        try {
            mRealm.createAllFromJson(Quote.class, is);
            mRealm.commitTransaction();
            Log.d(TAG, "onCreate: Transaction committed!");
            Log.d(TAG, "path: " + mRealm.getPath());
        } catch (IOException e) {
            mRealm.cancelTransaction();
            Log.d(TAG, "onCreate: Transaction cancelled!");
        }
        */