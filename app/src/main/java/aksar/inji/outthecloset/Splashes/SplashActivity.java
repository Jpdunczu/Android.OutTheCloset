package aksar.inji.outthecloset.Splashes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import aksar.inji.outthecloset.BrandListActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        startActivity(new Intent(SplashActivity.this, BrandListActivity.class));
        // close splash activity
        finish();
    }
}
