package br.com.sankhya.network;

import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class NetWorkSetup {

    public static final String ACCESS_TOKEN = "X-Auth-Token";
    public static final String CONTENT_TYPE = "Content-type";
    public static boolean mApplicationToken;

    private String mAccessToken = null;
    private String mAuthorization = null;

    public static final OkHttpClient getClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(requestIntercept)
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

    }


    public static HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return mLogging;
    }

    public static final Interceptor requestIntercept = new Interceptor() {
        private String mAccessToken = null;
        private String mAuthorization = "application/json";

        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();


            Request.Builder builder = request.newBuilder();

            builder.addHeader(CONTENT_TYPE, mAuthorization);

            Request requestFinal = builder.build();
            Response response = chain.proceed(requestFinal);
            mAccessToken = response.header(ACCESS_TOKEN);



            return response;

        }


        private String generateNextRequestAuthorizationHeader(String mAccessToken) throws UnsupportedEncodingException {
            String text = null;
            if ( mAccessToken != null) {
                String tokenToEncode = mAccessToken;
                text = Base64.encodeToString(tokenToEncode.getBytes(), Base64.DEFAULT);
            }
            return text;
        }
    };
    private static boolean isEmpty(String str) {
        if (str == null) return true;
        return str.trim().length() == 0;
    }
    public static void setContentType(String contentType){
        Environment.contentType = contentType;
    }





    public static class Environment {
        private static String msisdn;
        public static String contentType;
    }        private static String contentType;

        private static String currentToken;
        private static String nextToken;

        private static final String buildToken() {
            if (isEmpty(nextToken)) {
//                Log.d(TAG, "Empty X_UID or x-access-token");
                return "";
            } else {
                try {
                    final String authToken =  nextToken;
                    final byte[] data = authToken.getBytes("UTF-8");
                    String authorization = "Bearer"+ Base64.encodeToString(data, Base64.DEFAULT);
                    return authorization;
                } catch (UnsupportedEncodingException e) {
//                    Log.d(TAG, "Unsupported Encoding Exception");
                    return "";
                }
            }
        }



        public static final String buildMsisdn() {
            return null;
        }

        public static void setMsisdn(String msisdn) {
            Environment.msisdn = msisdn;
        }

        public static String getMsisdn() {
            return Environment.msisdn;
        }

}
