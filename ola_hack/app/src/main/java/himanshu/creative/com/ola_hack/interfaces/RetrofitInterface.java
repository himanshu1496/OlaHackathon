package himanshu.creative.com.ola_hack.interfaces;

import java.util.HashMap;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface RetrofitInterface {

    @GET("/")
    void postBookRequest(@QueryMap HashMap<String, String> params, Callback<Object> cb);

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