package himanshu.creative.com.ola_hack.utils;

import retrofit.Retrofit;

public class Constants {

    public static final int REQ_CODE_SPEECH_INPUT = 0;

    public static final String baseURL = "http://api.grabhouse.com/dcapp";
    public static final String loginURL = "http://sandbox-t.olacabs.com/oauth2/authorize?response_type=token&client_id=OGExY2VlMzQtZThjOS00NjQ2LWE5ZmEtMmI4ZDdjMTJhNTVh&redirect_uri=http://localhost/team39&scope=profile%20booking&state=state123";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .build();

}
