package com.example.youtube_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    Button btnsignup,btnsignin;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username_txt);
        password = findViewById(R.id.Password_txt);
        btnsignup = (Button)findViewById(R.id.btnsignup);
        btnsignin =(Button)findViewById(R.id.btnLogin);
        btnsignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signup();
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signin();
            }
        });
    }
    public void signup()
    {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }
    public void signin()
    {
        try
        {
            String url = "http:/192.168.1.5/youtube/signin.php";
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    try
                    {
                        if(response.equals("1 results"))
                        {
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "unsuccess", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }, new com.android.volley.Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    // method to handle errors.
                    Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", username.getText().toString());
                    params.put("password", password.getText().toString());
                    return params;
                }
            };
            queue.add(request);
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this,e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}