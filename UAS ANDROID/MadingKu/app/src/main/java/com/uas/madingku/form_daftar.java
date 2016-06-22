 package com.uas.madingku;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class form_daftar extends AppCompatActivity {

    ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText id_anggota, nama, kampus, no_hp, password, email;
    Button DaftarM, btnKembali;
    private static String url = "http://192.168.43.137/MadingKu/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_daftar);

        id_anggota   =(EditText)findViewById(R.id.anggota);
        nama         =(EditText)findViewById(R.id.nama);
        kampus       =(EditText)findViewById(R.id.kampus);
        no_hp        =(EditText)findViewById(R.id.no_hp);
        password     =(EditText)findViewById(R.id.password);
        email        =(EditText)findViewById(R.id.email);

        DaftarM      = (Button)findViewById(R.id.DaftarM);
        btnKembali  = (Button)findViewById(R.id.btnKembaliD);

        final EmailValidator emailValid = new EmailValidator();
        DaftarM.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (!emailValid.validate(email.getText().toString())) {
                        Toast.makeText(form_daftar.this, "Emailnya ga valid", Toast.LENGTH_LONG).show();
                        email.setText("");
                    } else {
                        new daftarAku().execute();
                    }

                }
        });

    }

    public class daftarAku extends AsyncTask<String, String, String>
    {

        String success;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(form_daftar.this);
            pDialog.setMessage("Proses mendaftar...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String strid_anggota = id_anggota.getText().toString();
            String strnama      = nama.getText().toString();
            String strkampus    = kampus.getText().toString();
            String strno_hp     = no_hp.getText().toString();
            String strpassword  = password.getText().toString();
            String stremail     = email.getText().toString();


            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
            nvp.add(new BasicNameValuePair("id_anggota", strid_anggota));
            nvp.add(new BasicNameValuePair("nama", strnama));
            nvp.add(new BasicNameValuePair("kampus", strkampus));
            nvp.add(new BasicNameValuePair("no_hp", strno_hp));
            nvp.add(new BasicNameValuePair("password", strpassword));
            nvp.add(new BasicNameValuePair("email", stremail));

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", nvp);
            try {
                success = json.getString("success");


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        protected void onPostExecute(String url) {
            // dismiss the dialog once done
            pDialog.dismiss();

            if (success.equals("1")) {
                Toast.makeText(getApplicationContext(), "Regitrasi sukses", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Registrasi gagal", Toast.LENGTH_LONG).show();
            }
        }

    }
    public class EmailValidator{

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator()
        {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        public boolean validate(final String hex)
        {
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }
    }
}
