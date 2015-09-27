package himanshu.creative.com.ola_hack.interfaces;

import java.util.HashMap;

import himanshu.creative.com.ola_hack.modals.CabDetailsModel;
import himanshu.creative.com.ola_hack.modals.OnSuccess;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface RetrofitInterface {

    @GET("/command")
    void postBookRequest(@QueryMap HashMap<String, String> params, Callback<OnSuccess> cb);

    @GET("/book")
    void postBookNowRequest(@QueryMap HashMap<String, String> params, Callback<OnSuccess> cb);

    @GET("/index")
    void getCabDetails(@QueryMap HashMap<String, String> params, Callback<CabDetailsModel> cb);

//    @GET("/getAppointments")
//    void getAppointments(@Header("token") String token, Callback<List<AppointmentModel>> callback);
//
//    @POST("/saveAppointment")
//    void saveAppointments(@Header("token") String token, @Body PropertyListingModel propertyListingModel, Callback<Object> cb);
//
//    @POST("/rescheduleAppointment")
//    void rescheduleAppointments(@Header("token") String token, @Body AppointmentModel appointmentModel, Callback<Object> cb);
//
//
}
