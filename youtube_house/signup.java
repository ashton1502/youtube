package com.example.youtube_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends YouTubeBaseActivity
{
    Button btn;
    EditText name,email,password,cnfrm_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn = (Button)findViewById(R.id.btnsignup);
        name = findViewById(R.id.name_txt);
        email = findViewById(R.id.email_txt);
        password = findViewById(R.id.Password_txt);
        cnfrm_pass = findViewById(R.id.cnfrm_Password_txt);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!password.getText().toString().equals(cnfrm_pass.getText().toString()))
                {
                    Toast.makeText(signup.this,"Password and confirm password must be same", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    postDataUsingVolley(name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim());
                }
            }
        });
    }
    private void postDataUsingVolley(final String name, String email, String password)
    {
        String url = "http:/192.168.137.250/youtube/signup.php";
        RequestQueue queue = Volley.newRequestQueue(signup.this);
        StringRequest request = new StringRequest(Request.Method.POST,url, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //loadingPB.setVisibility(View.GONE);
                try
                {
                    JSONObject respObj = new JSONObject(response);
                    //String result = respObj.getString("result");
                    //String name = respObj.getString("name");
                    //String email = respObj.getString("email");
                    //String password = respObj.getString("password");
                    //Toast.makeText(signup.this, "Inserted , " + name, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(signup.this,response, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    //youtube.setText("abc");
                }
            }
        }, new com.android.volley.Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                // method to handle errors.
                Toast.makeText(signup.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                // put some code for verfication that the post came from your mobile app
                //params.put("id",email);
                //params.put("pass",password);
                // at last we are
                // returning our params.
                return params;
            }
        };
        queue.add(request);
    }
}