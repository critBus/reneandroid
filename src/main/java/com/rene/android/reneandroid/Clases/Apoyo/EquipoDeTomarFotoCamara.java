package com.rene.android.reneandroid.Clases.Apoyo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.rene.android.reneandroid.Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rene on 2/7/2020.
 */

public class EquipoDeTomarFotoCamara {
    private Uri photoUri;
    private String mCurrentPhotoPath;
    private Utilizar<Uri> accionTomarFotoCamara;

    public EquipoDeTomarFotoCamara(Utilizar<Uri> accionTomarFotoCamara) {
        this.accionTomarFotoCamara = accionTomarFotoCamara;
    }

    private  File createImageFile(Activity a)throws IOException {
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName="Backup_"+timeStamp+"_";
        File storageDir=a.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(imageFileName,".jpg",storageDir);
        mCurrentPhotoPath=image.getAbsolutePath();
        return image;
    }
    public void tomarFoto(Activity a,int REQUEST_TAKE_FOTO){
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(a.getPackageManager())!=null){
            File photoFile=null;
            try{
                photoFile=createImageFile(a);
            }catch (Exception ex){ex.printStackTrace();}
            if (photoFile!=null){
                System.out.println("salida: va tomar la foto");
               // photoUri= FileProvider.getUriForFile(a,"com.rene.android.fileprovider",photoFile);
                photoUri= FileProvider.getUriForFile(a,a.getApplicationContext().getPackageName()+".fileprovider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                a.startActivityForResult(takePictureIntent,REQUEST_TAKE_FOTO);
                System.out.println("salida: lanzo la activity");
            }
        }
    }

    public void accionDespuesDeTomarLaFoto(){
        accionTomarFotoCamara.utilizar(getPhotoUri());
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public String getmCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public Utilizar<Uri> getAccionTomarFotoCamara() {
        return accionTomarFotoCamara;
    }

    public void setAccionTomarFotoCamara(Utilizar<Uri> accionTomarFotoCamara) {
        this.accionTomarFotoCamara = accionTomarFotoCamara;
    }
}
