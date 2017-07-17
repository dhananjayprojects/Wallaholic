package com.imagineappdev.wallaholic;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imagineappdev.wallaholic.mCloud.CloudinaryClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

import uk.co.senab.photoview.PhotoView;

import static com.imagineappdev.wallaholic.R.id.wallpaper_activity;
import static java.util.logging.Logger.global;

public class WallpaperActivity extends AppCompatActivity{

    public static Context contextOfApplication;
    private static final String TAG = "";
    String url,imageName;
    StringBuilder imageNameBuilder;

    RelativeLayout wallpaperActivityParentLayout;

    FavouriteDbHelper mFavouriteDbHelper;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        contextOfApplication = getApplicationContext();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        /*DatabaseReference globalFav = mDatabase.child("global-Fav").child(postRef.getKey());
        DatabaseReference userFav = mDatabase.child("user-Fav").child(model.uid).child(postRef.getKey());*/

        mFavouriteDbHelper = new FavouriteDbHelper(this);

        url = getIntent().getStringExtra("url");
        final ImageView fav = (ImageView)findViewById(R.id.fav);

        final PhotoView imgFull = (PhotoView) findViewById(R.id.imgFull);
        Picasso.with(this).load(url).into(imgFull);

        imageNameBuilder = new StringBuilder(url.replaceAll(".*/",""));
        imageNameBuilder.append(".jpg");
        imageName = imageNameBuilder.toString();
        final String imageNm = imageName.substring(0,imageName.length()-4);
        Log.d("imageName",imageName);
        LinearLayout setWallpaper = (LinearLayout) findViewById(R.id.SetWallpaper);
        LinearLayout downloadWallpaper = (LinearLayout) findViewById(R.id.DownloadWallpaper);
        final LinearLayout shareWallpaper = (LinearLayout) findViewById(R.id.shareWallpaper);
        LinearLayout favWallpaper = (LinearLayout)findViewById(R.id.favWallpaper);

        final boolean[] checkFav = {mFavouriteDbHelper.CheckIsDataAlreadyInDBorNot(url)};

        if(checkFav[0]){
            fav.setImageResource(R.drawable.ic_favorite_white_24dp);
        }
        else {
            fav.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }

        wallpaperActivityParentLayout = (RelativeLayout) findViewById(wallpaper_activity);
        setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_wallpaper();
            }
        });

        downloadWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_wallpaper();
            }
        });

        favWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkFav[0]){
                    fav.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    mFavouriteDbHelper.removeData(url);
                    checkFav[0] = mFavouriteDbHelper.CheckIsDataAlreadyInDBorNot(url);
                }
                else {
                    fav.setImageResource(R.drawable.ic_favorite_white_24dp);
                    addData(url);
                    checkFav[0] = mFavouriteDbHelper.CheckIsDataAlreadyInDBorNot(url);
                }

                /*onStarClicked(globalFav);
                onStarClicked(userFav);*/
            }
        });





        shareWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isStoragePermissionGranted();
                Picasso.with(getApplicationContext())
                        .load(CloudinaryClient.getImages(url))
                        .into(new Target() {
                                  @Override
                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                      File myDir = new File(
                                              Environment
                                                      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Android Wallpapers"
                                      );

                                      myDir.mkdirs();
                                      File file = new File(myDir, imageName);
                                      if (file.exists())
                                          file.delete();
                                      try {
                                          FileOutputStream out = new FileOutputStream(file);
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                          out.flush();
                                          out.close();
                                          Toast.makeText(
                                                  getApplicationContext(),"Wallpaper saved",
                                                  Toast.LENGTH_SHORT).show();

                                          MediaScannerConnection.scanFile(getApplicationContext(),
                                                  new String[]{file.toString()}, null,
                                                  new MediaScannerConnection.OnScanCompletedListener() {
                                                      public void onScanCompleted(String path, Uri uri) {
                                                          Log.i("ExternalStorage", "Scanned " + path + ":");
                                                          Log.i("ExternalStorage", "-> uri=" + uri);
                                                      }
                                                  });

                                          Uri uri = Uri.fromFile(file);

                                          Intent share = new Intent(Intent.ACTION_SEND);
                                          share.setType("image/jpeg");
                                          share.putExtra(Intent.EXTRA_TEXT, "Checkout hd wallpapers like this, on Wallaholic App https://play.google.com/store/apps/details?id=com.imagineappdev.wallaholic");
                                          share.putExtra(Intent.EXTRA_STREAM, uri);
                                          startActivity(Intent.createChooser(share, "Share Image"));


                                          Log.d(TAG, "Wallpaper saved to: " + file.getAbsolutePath());

                                      } catch (Exception e) {
                                          e.printStackTrace();
                                          Toast.makeText(getApplicationContext(),"Wallpaper not saved",
                                                  Toast.LENGTH_SHORT).show();
                                      }
                                  }

                                  @Override
                                  public void onBitmapFailed(Drawable errorDrawable) {
                                  }

                                  @Override
                                  public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  }
                              }
                        );
            }
        });

    }

    public void addData(String newEntry1){
        boolean insertData = mFavouriteDbHelper.addData(newEntry1);
        if (insertData){
            Toast.makeText(this,"Data successfully inserted!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Data cannot be inserted!",Toast.LENGTH_SHORT).show();
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    public void download_wallpaper(){
        isStoragePermissionGranted();
        Picasso.with(getApplicationContext())
                .load(CloudinaryClient.getImages(url))
                .into(new Target() {
                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              File myDir = new File(
                                      Environment
                                              .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Android Wallpapers"
                              );

                              myDir.mkdirs();
                              File file = new File(myDir, imageName);
                              if (file.exists())
                                  file.delete();
                              try {
                                  FileOutputStream out = new FileOutputStream(file);
                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                  out.flush();
                                  out.close();
                                  Toast.makeText(
                                          getApplicationContext(),"Wallpaper saved",
                                          Toast.LENGTH_SHORT).show();

                                  MediaScannerConnection.scanFile(getApplicationContext(),
                                          new String[]{file.toString()}, null,
                                          new MediaScannerConnection.OnScanCompletedListener() {
                                              public void onScanCompleted(String path, Uri uri) {
                                                  Log.i("ExternalStorage", "Scanned " + path + ":");
                                                  Log.i("ExternalStorage", "-> uri=" + uri);
                                              }
                                          });

                                          Snackbar snackbar = Snackbar
                                                  .make(wallpaperActivityParentLayout, "Wallpaper downloaded", Snackbar.LENGTH_LONG)
                                                  .setAction("SET", new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          set_wallpaper();
                                                      }
                                                  });
                                          snackbar.show();

                                  Log.d(TAG, "Wallpaper saved to: " + file.getAbsolutePath());

                              } catch (Exception e) {
                                  e.printStackTrace();
                                  Toast.makeText(getApplicationContext(),"Wallpaper not saved",
                                          Toast.LENGTH_SHORT).show();
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                          }
                      }
                );

    }

    public void set_wallpaper(){
        isStoragePermissionGranted();
        Picasso.with(getApplicationContext())
                .load(CloudinaryClient.getImages(url))
                .into(new Target() {
                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              File myDir = new File(
                                      Environment
                                              .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Android Wallpapers"
                              );

                              myDir.mkdirs();
                              File file = new File(myDir, imageName);
                              if (file.exists())
                                  file.delete();
                              try {
                                  FileOutputStream out = new FileOutputStream(file);
                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                  out.flush();
                                  out.close();
                                  Toast.makeText(
                                          getApplicationContext(),"Wallpaper saved",
                                          Toast.LENGTH_SHORT).show();

                                  MediaScannerConnection.scanFile(getApplicationContext(),
                                          new String[]{file.toString()}, null,
                                          new MediaScannerConnection.OnScanCompletedListener() {
                                              public void onScanCompleted(String path, Uri uri) {
                                                  Log.i("ExternalStorage", "Scanned " + path + ":");
                                                  Log.i("ExternalStorage", "-> uri=" + uri);
                                              }
                                          });
                                  /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                      WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                                      Uri contentURI = getImageContentUri(getApplicationContext(), file.getAbsolutePath());

                                      Intent intent = new Intent(wallpaperManager.getCropAndSetWallpaperIntent(contentURI));
                                      startActivity(intent);
                                  }
                                  else {*/
                                      Uri uri = Uri.fromFile(file);
                                      Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
                                      intent.addCategory(Intent.CATEGORY_DEFAULT);
                                      intent.setDataAndType(uri, "image/*");
                                      intent.putExtra("mimeType", "image/*");
                                      startActivity(Intent.createChooser(intent, "Set as:"));

                                      Log.d(TAG, "Wallpaper saved to: " + file.getAbsolutePath());
                                  /*}*/

                              } catch (Exception e) {
                                  e.printStackTrace();
                                  Toast.makeText(getApplicationContext(),"Wallpaper not saved",
                                          Toast.LENGTH_SHORT).show();
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                          }
                      }
                );

    }

    public static Uri getImageContentUri(Context context, String absPath) {
        Log.v(TAG, "getImageContentUri: " + absPath);

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , new String[] { MediaStore.Images.Media._ID }
                , MediaStore.Images.Media.DATA + "=? "
                , new String[] { absPath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , Integer.toString(id));

        } else if (!absPath.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, absPath);
            return context.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } else {
            return null;
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }





}
