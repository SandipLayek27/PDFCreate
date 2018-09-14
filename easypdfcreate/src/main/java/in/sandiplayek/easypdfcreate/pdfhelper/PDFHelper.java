package in.sandiplayek.easypdfcreate.pdfhelper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PDFHelper {

    public static void openFile (Context activity, File file, String string) {

        Intent intent = new Intent();
        intent.putExtra("path", file.getAbsolutePath());
        intent.putExtra("name", file.getName());
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
            intent.setDataAndType(contentUri,string);
        } else {
            intent.setDataAndType(Uri.fromFile(file),string);
        }

        try {
            activity.startActivity (intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "Please install an app to perform action.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void pdf_backup (String outPutPath,String title,String password) {
        InputStream in;
        OutputStream out;

        try {

            in = new FileInputStream(outPutPath);
            out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/pdf_backups/" + title);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();

            // write the output file
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }

    public static void deleteFile(File fileOrDirectory) {
        try{
            fileOrDirectory.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
