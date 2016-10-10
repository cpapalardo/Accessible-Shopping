package com.project.tcc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback{

    private NfcAdapter nfcAdapter;
    TextView textViewRespostaNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewRespostaNFC = (TextView) findViewById(R.id.textViewRespostaNFC);
    }

    public void iniciarActivityMenu(View v){
        Intent intent = new Intent(this, MenuCoresActivity.class);
        startActivity(intent);
    }

    public void iniciarMapLojasMenu(View v){
        Intent intent = new Intent(this, MapLojasActivity.class);
        startActivity(intent);
    }

    private boolean filterMessage(NdefMessage message) {
        if (message == null)
            return false;

        NdefRecord ndefRecord = null;

        try {
            ndefRecord = message.getRecords()[0];
        } catch (Throwable ex) {
            //apenas ignora
        }

        if (ndefRecord != null &&
                ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN &&
                ndefRecord.getType().length == 1 &&
                ndefRecord.getType()[0] == NdefRecord.RTD_TEXT[0]) {

            byte[] payload = ndefRecord.getPayload();

				/*
				0 = n (tamanho do locale)
				1
				...
				n = locale
				n+1 em diante: mensagem (utf-8)
				*/
            if (payload != null &&
                    payload.length >= 4) {

                int localeLength = payload[0];
                String locale = new String(payload, 1, localeLength);
                String text = new String(payload, 1 + localeLength, payload.length - 1 - localeLength);
                System.out.println("MIME: " + ndefRecord.toMimeType());
                System.out.println("Locale: " + locale);
                System.out.println("Text: " + text);

                setTextRoupa(textViewRespostaNFC, text);
                return true;
            }
        }

        return false;
    }

    public void setTextoRoupa(String textoRoupa){
        textViewRespostaNFC.setText(textoRoupa);
    }

    @Override
    protected void onResume() {
        super.onResume();

        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplication());
        nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B | NfcAdapter.FLAG_READER_NFC_BARCODE | NfcAdapter.FLAG_READER_NFC_F | NfcAdapter.FLAG_READER_NFC_V | NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS, new Bundle());

        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                for (int i = 0; i < rawMsgs.length; i++) {
                    if (filterMessage((NdefMessage)rawMsgs[i])) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);

        Ndef ndef = Ndef.get(tag);
        NdefMessage message = null;
        try {
            message = ndef.getNdefMessage();
        } catch (Throwable ex) {
            //apenas ignora!
        }
        if (message == null) {
            try {
                message = ndef.getCachedNdefMessage();
            } catch (Throwable ex) {
                //apenas ignora!
            }
        }
        filterMessage(message);
    }

    private void setTextRoupa(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
}
