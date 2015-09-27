package himanshu.creative.com.ola_hack.fragment;



import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
    @Bind(R.id.main_layout)
    RelativeLayout mainLayout;
    @Bind(R.id.vehnum)
    TextView vehiclleNum;
    @Bind(R.id.category)
    TextView category;
    @Bind(R.id.carmodel)
    TextView carModel;
    @Bind(R.id.phonenum)
    TextView phoneNumber;
    @Bind(R.id.drivname)
    TextView driverName;
    @Bind(R.id.time)
    TextView pickUpTime;


    @Bind(R.id.indeterminate_horizontal_progress)
    ProgressBar progressBar;

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
        progressBar.setVisibility(View.VISIBLE);
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
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("Failed", "");
                Toast.makeText(activity, "Oops! Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
    void fillDetails(final RideModel rideModel) {
        mainLayout.setVisibility(View.VISIBLE);
        if (rideModel != null) {

                if (rideModel.getCab_number()!=null) {
                    vehiclleNum.setText(rideModel.getCab_number().toUpperCase());
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
            if (rideModel.getCategory()!=null) {
                category.setText(rideModel.getCategory());
                category.setVisibility(View.VISIBLE);
            } else {
                category.setVisibility(View.INVISIBLE);
            }
            if (rideModel.getPickup_time()!=null) {
                pickUpTime.setText(rideModel.getPickup_time());
                pickUpTime.setVisibility(View.VISIBLE);
            } else {
                pickUpTime.setVisibility(View.INVISIBLE);
            }
            if (rideModel.getDriver_number()!=null) {
                phoneNumber.setVisibility(View.VISIBLE);
                phoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + rideModel.getDriver_number()));
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
            } else {
                phoneNumber.setVisibility(View.INVISIBLE);
            }




        } else {
            Toast.makeText(activity, "Oops! Something went wrong", Toast.LENGTH_LONG).show();
        }
    }




}
