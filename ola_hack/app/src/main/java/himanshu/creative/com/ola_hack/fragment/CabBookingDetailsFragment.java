package himanshu.creative.com.ola_hack.fragment;



import android.support.v4.app.Fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import himanshu.creative.com.ola_hack.R;
import himanshu.creative.com.ola_hack.interfaces.RetrofitInterface;
import himanshu.creative.com.ola_hack.modals.CabDetailsModel;
import himanshu.creative.com.ola_hack.modals.RideModel;
import himanshu.creative.com.ola_hack.utils.Constants;
import himanshu.creative.com.ola_hack.utils.Methods;
import himanshu.creative.com.ola_hack.utils.PreferenceUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



public class CabBookingDetailsFragment extends Fragment {
    Activity activity;
    @Bind(R.id.top_layout)
    LinearLayout topLayout;
    @Bind(R.id.vehnum)
    TextView vehiclleNum;
    @Bind(R.id.carmodel)
    TextView carModel;
    @Bind(R.id.phonenum)
    TextView phoneNumber;
    @Bind(R.id.drivname)
    TextView driverName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        activity = (AppCompatActivity) getActivity();
        Methods.checkAndAskForGPS(activity);
        View rootView = inflater.inflate(R.layout.fragment_view_booking, container, false);
        ButterKnife.bind(this, rootView);
        fetchDetails();

        return rootView;

    }
    void fetchDetails() {
        RetrofitInterface retrofitInterface = Constants.retrofit.create(RetrofitInterface.class);
        HashMap<String, String> params = new HashMap<String, String>();
        /* params.put(Constants.keyCommand, Constants.commandAvailability);
        params.put(Constants.keyLat, String.valueOf(gpsLocation.getLatitude()));
        params.put(Constants.keyLong, String.valueOf(gpsLocation.getLongitude())); */
        retrofitInterface.bookedCabDetails(params, new Callback<RideModel>() {
            @Override
            public void success(RideModel rideModel, Response response) {
                Log.i("Successfully fetched data", "");

                fillDetails(rideModel);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Failed", "");
            }
        });
    }
    void fillDetails(RideModel rideModel) {
        topLayout.setVisibility(View.VISIBLE);
        if (rideModel != null) {

                if (rideModel.getCab_number()!=null) {
                    vehiclleNum.setText(rideModel.getCab_number());
                    vehiclleNum.setVisibility(View.VISIBLE);
                } else {
                    vehiclleNum.setVisibility(View.INVISIBLE);
                }
            if (rideModel.getCar_model()!=null) {
                carModel.setText(rideModel.getCar_model());
                carModel.setVisibility(View.VISIBLE);
            } else {
                carModel.setVisibility(View.INVISIBLE);
            }
            if (rideModel.getDriver_name()!=null) {
                driverName.setText(rideModel.getDriver_name());
                driverName.setVisibility(View.VISIBLE);
            } else {
                driverName.setVisibility(View.INVISIBLE);
            }
            if (rideModel.getDriver_number()!=null) {
                phoneNumber.setText(rideModel.getDriver_number());
                phoneNumber.setVisibility(View.VISIBLE);
            } else {
                phoneNumber.setVisibility(View.INVISIBLE);
            }



        } else {
            Toast.makeText(activity, "Oops! Something went wrong", Toast.LENGTH_LONG).show();
        }
    }




}
