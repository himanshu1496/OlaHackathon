package himanshu.creative.com.ola_hack.fragment;

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
import himanshu.creative.com.ola_hack.utils.Constants;
import himanshu.creative.com.ola_hack.utils.Methods;
import himanshu.creative.com.ola_hack.utils.PreferenceUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CabDetailsFragment extends Fragment implements EditText.OnEditorActionListener, LocationListener {

    Activity activity;
    Location gpsLocation;

    @Bind(R.id.txtSpeechInput)
    EditText txtSpeechInput;
    @Bind(R.id.btnSpeak)
    ImageButton btnSpeak;
    @Bind(R.id.top_layout)
    LinearLayout topLayout;
    @Bind(R.id.confirm_button)
    TextView confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (AppCompatActivity) getActivity();
        Methods.checkAndAskForGPS(activity);
        View rootView = inflater.inflate(R.layout.fragment_cab_details, container, false);
        ButterKnife.bind(this, rootView);

        try {
            LocationManager mLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (gpsLocation.getLatitude() == 0)
                gpsLocation = location;
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
                    50, this);

        } catch (Exception e){
            e.printStackTrace();
        }


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtSpeechInput.setText("Type Here");
                txtSpeechInput.setVisibility(View.VISIBLE);
                topLayout.setVisibility(View.INVISIBLE);
                btnSpeak.setEnabled(false);
                promptSpeechInput();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public void onClick(View v) {
                if (gpsLocation.getLatitude() == 0 && gpsLocation.getLongitude() == 0){
                    Methods.checkAndAskForGPS(activity);
                } else {
                    //TODO Make network request here
                    RetrofitInterface retrofitInterface = Constants.retrofit.create(RetrofitInterface.class);
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put(Constants.keyAccessTaken, PreferenceUtils.getAuthToken());
                    params.put(Constants.keyLat, String.valueOf(gpsLocation.getLatitude()));
                    params.put(Constants.keyLong, String.valueOf(gpsLocation.getLongitude()));
                    params.put(Constants.keyCommand, txtSpeechInput.getText().toString());
                    retrofitInterface.postBookRequest(params, new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {
                            Log.i("Success","");
                            if (o != null)
                                Log.i(o.toString(),"");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.i("Failed","");
                            Toast.makeText(activity, "Oops! Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

        return rootView;
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        btnSpeak.setEnabled(true);
        try {
            startActivityForResult(intent, Constants.REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(activity,
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constants.REQ_CODE_SPEECH_INPUT: {
                if (resultCode == activity.RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    confirmButton.setVisibility(View.VISIBLE);
                }
                break;
            }

        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getText().toString().length() > 0){
            confirmButton.setVisibility(View.VISIBLE);
        } else {
            confirmButton.setVisibility(View.INVISIBLE);
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        gpsLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
