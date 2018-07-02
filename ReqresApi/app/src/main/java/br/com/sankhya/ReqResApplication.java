package br.com.sankhya;

import android.app.Application;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.inject.Inject;

import br.com.sankhya.business.AmbienteManager;
import br.com.sankhya.di.app.AppComponent;
import br.com.sankhya.di.app.AppModule;
import br.com.sankhya.di.app.DaggerAppComponent;
import br.com.sankhya.network.APIClient;
import br.com.sankhya.utils.Utils;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ReqResApplication extends Application {

    private static ReqResApplication instance;
    private AppComponent mAppComponent;
    private HashMap<String, Object> attributes = new HashMap<String, Object>();
    @Inject
    public APIClient mApiClient;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public  static ReqResApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AmbienteManager ambienteManager = AmbienteManager.getInstance();
        ambienteManager.startSession( getApplicationContext() );
        put(AmbienteManager.KEY, ambienteManager);

        initializeInjector();
        initializeCalligraphy();
        initializeAPIService();
    }

    private void initializeAPIService() {
        Format dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        String pattern = ((SimpleDateFormat) dateFormat).toLocalizedPattern();
        Converter.Factory converter = GsonConverterFactory.create(Utils.getGsonDate(pattern));

        mApiClient = new APIClient(converter,getString(R.string.url_reqres_api), this);
        mApiClient.setContentType(getString(R.string.content_type));


    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault( new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public void put(final String key, final Object value) {
        this.attributes.put(key, value);
    }

    public Object get(final String key) {
        return this.attributes.get(key);
    }

    public APIClient getApiClient() {
        return mApiClient;
    }
}
