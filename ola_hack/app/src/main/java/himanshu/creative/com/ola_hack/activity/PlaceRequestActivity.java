package himanshu.creative.com.ola_hack.activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import himanshu.creative.com.ola_hack.R;
import himanshu.creative.com.ola_hack.fragment.CabDetailsFragment;

public class PlaceRequestActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_a_request);
        getSupportActionBar().hide();
        FragmentManager fragmentManager = getSupportFragmentManager();
        CabDetailsFragment cabDetailsFragment = new CabDetailsFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.place_request_container, cabDetailsFragment)
                .commit();
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
}
