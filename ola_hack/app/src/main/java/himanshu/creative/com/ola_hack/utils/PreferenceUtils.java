package himanshu.creative.com.ola_hack.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import himanshu.creative.com.ola_hack.OlaHackApplication;

public class PreferenceUtils {
    public static final String MyPREFERENCES = "OlaHackPrefs" ;
    public static final String AUTH_TOKEN = "authToken";
    public static final String EMPTY_AUTH_TOKEN = "0";
    private static final String USER_LOGGED = "user_logged";
    private static final String DROPPED_LATITUDE = "dropped_latitude";
    private static final String DROPPED_LONGITUDE = "dropped_longitude";
    private static final String USER_NAME = "user_name";
    private static final String NAME = "name";
    private static final String USER_TYPE = "user_type";
    private static SharedPreferences mPreferences;

    private static SharedPreferences getPreferenceFile() {
        if (null == mPreferences)
            mPreferences = OlaHackApplication.getAppContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return mPreferences;
    }

    public static boolean isUserLogged() {
        return getPreferenceFile().getBoolean(USER_LOGGED, false);
    }

    public static int setUserLogged(boolean value) {
        getPreferenceFile().edit().putBoolean(USER_LOGGED, value).apply();
        if (value == false){
            getPreferenceFile().edit().putString(AUTH_TOKEN, EMPTY_AUTH_TOKEN).apply();
        }
        return 1;
    }

    public static int setAuthToken(String value) {
        getPreferenceFile().edit().putString(AUTH_TOKEN, value).apply();
        if (value.equalsIgnoreCase(EMPTY_AUTH_TOKEN))
            setUserLogged(false);
        else
            setUserLogged(true);
        return 1;
    }

    public static String getAuthToken() {
        return getPreferenceFile().getString(AUTH_TOKEN, EMPTY_AUTH_TOKEN);
    }

    public static String getUsername(){
        return getPreferenceFile().getString(USER_NAME, "");
    }

    public static void setUsername(String username) {
        getPreferenceFile().edit().putString(USER_NAME, username).apply();
    }

    public static int setLatLong(Double lat, Double lon) {
        getPreferenceFile().edit().putString(DROPPED_LATITUDE, String.valueOf(lat)).apply();
        getPreferenceFile().edit().putString(DROPPED_LONGITUDE, String.valueOf(lon)).apply();

        return 1;
    }
    public static Location getDroppedLocation(){
        Location location = new Location("Dropped Location");
        location.setLatitude(Double.parseDouble(getPreferenceFile().getString(DROPPED_LATITUDE, "0")));
        location.setLongitude(Double.parseDouble(getPreferenceFile().getString(DROPPED_LONGITUDE, "0")));
        return location;
    }

}
