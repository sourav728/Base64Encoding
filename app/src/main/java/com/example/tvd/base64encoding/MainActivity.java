package com.example.tvd.base64encoding;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView encoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encoaded = (TextView) findViewById(R.id.txt_encoadedstring);
        //Here give the file location whatever you want to encode
        //And give the storage permission manually
        // Toast.makeText(this, "Base64 Encoading Started..", Toast.LENGTH_SHORT).show();
        File sdcard = Environment.getExternalStorageDirectory();
        File directory = new File(sdcard.getAbsoluteFile() + File.separator + "TRM_Smart_Billing" + File.separator + "data" + File.separator + "files" + File.separator + "databases" + File.separator + "mydb.db");
        byte[] bytes = new byte[0];
        try {
            bytes = loadFile(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        encoaded.setText(encodedString);
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        Log.d("Debug", "File length " + length);
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }
}
