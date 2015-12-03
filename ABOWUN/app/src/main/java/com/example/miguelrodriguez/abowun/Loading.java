package com.example.miguelrodriguez.abowun;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Loading extends AppCompatActivity {

    private AnimationDrawable mFrameAnimation;
    private ImageView mImageLoading;
    private RelativeLayout mContLoading;
    Uri uri;
    ArrayList<String> respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mContLoading = (RelativeLayout) findViewById(R.id.loadingLayout);
        mImageLoading = (ImageView) findViewById(R.id.loadingView);
        mImageLoading.setBackgroundResource(R.drawable.loading_animation);
        mFrameAnimation = (AnimationDrawable) mImageLoading.getBackground();

        uri=(Uri)getIntent().getExtras().get("encoded");
        System.out.println(getRealPathFromURI(uri));
        new LongOperation().execute("");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public  void getSpecie(final String toSend) {
        ArrayList<String> res=new ArrayList<String>();
        new Thread(new Runnable() {

            @Override
            public void run() {
                SoapRequest sr = new SoapRequest();
                respuesta=sr.sendImage(toSend);
                System.out.println("Apppppppppppppppppppp " + respuesta);
                handler.sendEmptyMessage(0);

            }
        }).start();


    }
    public Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {

                case 0:
                    //txt.setText(celsius);
                    break;
            }
            return false;
        }
    });
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //Start  Loading Animation
            mContLoading.setVisibility(View.VISIBLE);
            mFrameAnimation.start();
        }

        @Override
        protected String doInBackground(String... params) {
            //ToDo your Network Job/Request etc. here
            try {
            InputStream stream = getContentResolver().openInputStream(uri);
            Bitmap bitmap= BitmapFactory.decodeStream(stream);
            getSpecie(Base64.encodeToString(getBytesFromBitmap(bitmap), Base64.NO_WRAP));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            //ToDo with result you got from Task
            //Stop Loading Animation
            mFrameAnimation.stop();
            Intent intent = new Intent(Loading.this, ListActivity.class);
            intent.putExtra("respuesta",respuesta);
            startActivity(intent);

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

}
