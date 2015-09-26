package himanshu.creative.com.ola_hack.utils;


import retrofit.RestAdapter;

public class Constants {

    public static final int REQ_CODE_SPEECH_INPUT = 0;

    public static final String baseURL = "http://ola.geekd.in/api";
    public static final String loginURL = "http://sandbox-t.olacabs.com/oauth2/authorize?response_type=token&client_id=OGExY2VlMzQtZThjOS00NjQ2LWE5ZmEtMmI4ZDdjMTJhNTVh&redirect_uri=http://localhost/team39&scope=profile%20booking&state=state123";
    public static final String access_token = "Bearer 1e44ab4dedbc431aa969f930b8e5101f";

    //keys
    public static final String keyAccessTaken = "access_token";
    public static final String keyLat = "lat";
    public static final String keyLong = "lng";
    public static final String keyCommand = "command";


    public static RestAdapter retrofit = new RestAdapter.Builder()
            .setEndpoint(baseURL)
            .build();

}
