package com.example.helloworld;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp_Activity extends AppCompatActivity {

    EditText newUsername, newPassword;
    Button btnDaftar;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        sqliteHelper = new SqliteHelper(this);

        newUsername = (EditText)findViewById(R.id.newUsername);
        newPassword = (EditText)findViewById(R.id.newPassword);
        btnDaftar = (Button)findViewById(R.id.daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUsername.getText().toString().trim();
                String password = newPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (password.equals("") || username.equals("")){
                    Toast.makeText(SignUp_Activity.this, "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }
                else {
                    values.put(SqliteHelper.row_username, username);
                    values.put(SqliteHelper.row_password, password);
                    sqliteHelper.insertData(values);

                    Toast.makeText(SignUp_Activity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}