package in.sandiplayek.pdfcreator;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import in.sandiplayek.easypdfcreate.CreatePdfNormally;
import in.sandiplayek.easypdfcreate.CreatePdfPassword;

public class MainActivity extends AppCompatActivity{

    String paragraph ="Hello My Name is Sandip Layek";
    String title ="MyName";
    String password = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CreatePdfNormally createPdfNormally = new CreatePdfNormally(MainActivity.this);
        //createPdfNormally.create(paragraph,title);


        //CreatePdfPassword createPdfPassword = new CreatePdfPassword(MainActivity.this);
        //createPdfPassword.create(paragraph,title,password);

        try {
            String jsonString = AssetJSONFile("jsonfile.json", MainActivity.this);
            JSONObject jsonObject = new JSONObject(jsonString);
            String responseCode = jsonObject.getString("responseCode");
                if (responseCode.equals("200")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("withdrawReport");

                    if(jsonArray.length()>0){
                        //CreatePdfNormally createPdfNormally = new CreatePdfNormally(MainActivity.this);
                        //createPdfNormally.createJSONArrayToPDF(jsonArray,"ARRAYEXPORT");

                        //CreatePdfPassword createPdfPassword = new CreatePdfPassword(MainActivity.this);
                        //createPdfPassword.createJSONArrayToPDF(jsonArray,"ARRAYEXPORT","123");
                    }else{
                        Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }
}
