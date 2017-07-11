package com.abhimanyu.realtimetextscanner;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExtractedTextActivity extends AppCompatActivity {

    EditText textExtracted;
    Button copyText, shareText, retry, exit;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extracted_text);

        textExtracted = (EditText) findViewById(R.id.product);
        copyText = (Button) findViewById(R.id.copyText);
        shareText = (Button) findViewById(R.id.shareText);
        retry = (Button) findViewById(R.id.retry);
        exit = (Button) findViewById(R.id.exit);

        Toast.makeText(getApplicationContext(), " Welcome You Have Entered the Dragon !!", Toast.LENGTH_LONG).show();

        text = getIntent().getExtras().getString("ExtractedText");
        textExtracted.setText(text);

        copyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = textExtracted.getText().toString();
                copy();
            }
        });

        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = textExtracted.getText().toString();
                share();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RealTimeTextScanner.class));
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), " Thank You !!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void share() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharingIntent,"Send Message via:"));
        //Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message.getText().toString());
    }
    private void copy(){
        ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(text);
        Toast.makeText(getApplicationContext(), " Text Copied Successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), RealTimeTextScanner.class));
        finish();
    }
}
