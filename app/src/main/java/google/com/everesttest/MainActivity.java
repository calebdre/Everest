package google.com.everesttest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.harman.everestelite.Bluetooth;

import java.io.IOException;

public class MainActivity extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_pair);
        // Remember to check runtime permissions for 6.0+
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, 50);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("EVEREST", "clicked");
                    Bluetooth connector = new Bluetooth(new EverestBluetoothListener(MainActivity.this), MainActivity.this, true);
                    connector.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            Bluetooth connector = new Bluetooth(new EverestBluetoothListener(this), this, true);
            connector.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
