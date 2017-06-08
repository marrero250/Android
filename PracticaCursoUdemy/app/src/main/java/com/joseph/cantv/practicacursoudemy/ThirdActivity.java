package com.joseph.cantv.practicacursoudemy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTexPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    //Comprobar version actual de Android que estamos corriendo.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                     //COMPROBAR SI HA ACEPTADO O NO HA ACEPTADO, O NUNCA SE LE HA PREGUNTADO
                        if (CheckPermission(Manifest.permission.CALL_PHONE)){
                            //HA ACEPTADO
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("TEL" + phoneNumber));
                            if (ActivityCompat.checkSelfPermission(ThirdActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                            startActivity(i);
                        }
                        else {
                            // Ha denegado o es la primera vez que se le pregunta
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                // SI NO SE LE PREGUNTANDO AUN
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            }else {
                               //Ha denegado
                                Toast.makeText(ThirdActivity.this, "Please, enable the resquest permission", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }

                    } else {
                        OlderVersion(phoneNumber);
                    }

                }
                else {

                    Toast.makeText(ThirdActivity.this, "Insert a phone number", Toast.LENGTH_SHORT).show();

                }

            }

            private void OlderVersion(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the acces", Toast.LENGTH_SHORT).show();
                }
            }
        });
    //Boton para la direccion web
       imgBtnWeb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String url = editTextWeb.getText().toString();
               String email ="Joseph";
               if (url != null && !url.isEmpty()){
                   Intent intentWeb = new Intent();
                   intentWeb.setAction(Intent.ACTION_VIEW);
                   intentWeb.setData(Uri.parse("http://"+url));

                    //Contactos
                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                    //Email rapido
                   Intent intentMailto = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));
                   //Email Completo
                   Intent intentMail = new Intent(Intent.ACTION_VIEW, Uri.parse(email));
                   intentMail.setType("Plain/Text");
                   intentMail.putExtra(Intent.EXTRA_SUBJECT,"Mai's title");
                   intentMail.putExtra(Intent.EXTRA_TEXT, "Hi there, I love MyForm app, but....");
                   intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"fernandoŋ@gmail.com","antonio@gmail.com"});

                   //Telefono 2
                   Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("666111222"));
                   startActivity(intentPhone);

               }
           }
       });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Estamos en el caso del telefono

        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    //Comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Concedio su permiso
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel:" + phoneNumber));
                        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                        startActivity(intentCall);
                   }
                     else {
                       //No concedio su permiso
                       Toast.makeText(ThirdActivity.this, "You Declined the access", Toast.LENGTH_SHORT).show();
                   }
               }

               break;

           default:
               super.onRequestPermissionsResult(requestCode, permissions, grantResults);
               break;

        }


    }

    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }
}
