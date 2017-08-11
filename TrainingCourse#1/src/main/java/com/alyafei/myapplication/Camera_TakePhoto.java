package com.alyafei.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera_TakePhoto extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera__take_photo);
        imageView= (ImageView) findViewById(R.id.imgView);
    }
    int tag=1; //tag - If >= 0, this code will be returned is requestCode in onActivityResult() when the activity exits.

    public void buTakePhoto(View view) {
        CheckUserPermsions(); // the first thing..
    }

    /****
     *  Standard activity result: operation canceled.
     *  public static final int RESULT_CANCELED    = 0;
     *
     *  Standard activity result: operation succeeded.
     *  public static final int RESULT_OK           = -1;
     ****/

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data ){
        if(requestCode==tag &&resultCode==RESULT_OK ) { //requestCode==tag  because there may be 100 tags other than this.
            // get the data
            Bundle bundle= data.getExtras();
            Bitmap bitmap=(Bitmap)bundle.get("data"); // key "data" is for photo..
            imageView.setImageBitmap(bitmap);
        }
    }
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        takePhoto();//

    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;// you can put any thing ..



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto(); //gps call
                } else {
                    // Permission Denied
                    Toast.makeText( this,"ERROR! Permission is denied" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void takePhoto() {
        Intent intent= new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,tag);

    }
}
