package himanshu.creative.com.ola_hack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import himanshu.creative.com.ola_hack.R;
import himanshu.creative.com.ola_hack.utils.Methods;
import himanshu.creative.com.ola_hack.utils.PreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    Activity activity;
    ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        //TODO Remove if login feature implemented
        loginWithStaticAuth();

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        checkNetworkAndMoveOn();
    }

    private void checkNetworkAndMoveOn(){
        progressWheel.setVisibility(View.VISIBLE);

        if (Methods.isNetworkAvailable(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SplashActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (PreferenceUtils.isUserLogged()) {
                                Intent intent = new Intent(SplashActivity.this, PlaceRequestActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish();
                            } else {
                                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish();
                            }
                        }
                    });

                }
            }, 2000);
        } else {
            progressWheel.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_network_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loginWithStaticAuth(){
        PreferenceUtils.setAuthToken("d37a2b489daa43d288c81eeb6fb4b5a2");
    }
}
