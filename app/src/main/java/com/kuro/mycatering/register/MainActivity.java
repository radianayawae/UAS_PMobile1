package com.kuro.mycatering.register;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private Button btn_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan=findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v->{
            scanCode();
        });

    }

    private void scanCode() {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("Volume up to flash on");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(scanOptions);

    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(),result ->
    {
        if(result.getContents() !=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

        }
    });
}