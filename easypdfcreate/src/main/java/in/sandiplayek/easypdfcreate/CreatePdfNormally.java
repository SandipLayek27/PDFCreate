package in.sandiplayek.easypdfcreate;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import in.sandiplayek.easypdfcreate.pdfhelper.PDFHelper;

public class CreatePdfNormally{

    private String fileName = "";
    Context context;

    public CreatePdfNormally(Context context) {
        this.context = context;
    }

    public void create(String paragraph, String title){
        String docType = "normal";
        if(paragraph.equals("") || title.equals("")){
            Toast.makeText(context, "Enter Valid Data", Toast.LENGTH_SHORT).show();
            return;
        }
        createPdfFile(paragraph,title,docType);
    }

    public void createJSONArrayToPDF(JSONArray jsonArray, String title){

        if(jsonArray.equals("") || title.equals("") || jsonArray.length()<=0){
            Toast.makeText(context, "Enter Valid Data or No data found", Toast.LENGTH_SHORT).show();
            return;
        }
        String docType = "rotate";
        String paragraph = "";
        String separator = "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String space = "       ";
        String headerSpace = "           ";

        try{
            //CREATE HEADER PART
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Iterator<String> iter = jsonObject.keys();
            int i = 0;
            paragraph = separator;

            while (iter.hasNext()) {
                String key = iter.next();

                paragraph = paragraph + key +headerSpace;

                i++;
            }
            paragraph = paragraph+ separator;


            int j = 1;
            for(int k=0; k<jsonArray.length(); k++){
                JSONObject jsonObjectData = jsonArray.getJSONObject(k);
                Iterator<String> iterData = jsonObjectData.keys();

                JSONObject jsonObjectDataPart = jsonArray.getJSONObject(k);

                int m = 0;
                while (iterData.hasNext()) {
                    String key = iterData.next();
                    //String dataValue = jsonObjectDataPart.getString(key);
                    String dataValue = checkKeyAndGetValue(jsonObjectDataPart,key);
                    paragraph = paragraph+dataValue+space;
                    m++;
                }
                paragraph = paragraph + separator;
                j++;
            }
            createPdfFile(paragraph,title,docType);
        }catch (Exception e){
            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //Create PDF NORMALLY
    private void createPdfFile(String paragraph,String title,String documentType) {
        fileName = title + ".pdf";
        createPDF(fileName,paragraph,documentType);
        InputStream in;
        OutputStream out;

        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + fileName;

            in = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +  "/" + title);
            out = new FileOutputStream(path);

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

    //Create PDF NORMALLY
    private void createPDF(String title,String paragraph,String documentType) {
        // Output file
        String outputPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+title;

        // Run conversion
        final boolean result = convertToPdf(outputPath,paragraph,documentType);

        // Notify the UI
        if (result) {
            Toast.makeText(context, "Successfully Created", Toast.LENGTH_SHORT).show();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+title);
            PDFHelper.openFile(context, file, "application/pdf");
        } else Toast.makeText(context, "Not Success", Toast.LENGTH_SHORT).show();
    }

    //Create PDF NORMALLY
    private boolean convertToPdf(String outputPdfPath, String paragraph, String documentType) {
        try {
            // Create output file if needed
            File outputFile = new File(outputPdfPath);

            if (!outputFile.exists()) outputFile.createNewFile();

            Document document;

            if(documentType.equals("normal")){
                document = new Document(PageSize.A4);
            }else if(documentType.equals("rotate")){
                document = new Document(PageSize.A4.rotate());
            }else{
                document = new Document(PageSize.A4);
            }
            //document = new Document(PageSize.A4);
            //document = new Document(PageSize.A4.rotate());
            /*if (textTitle.getText().toString().equals("portrait")) {
                document = new Document(PageSize.A4);
            } else {
                document = new Document(PageSize.A4.rotate());
            }*/

            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            document.add (new Paragraph(paragraph));

            document.close();

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static String checkKeyAndGetValue(JSONObject jsonObject, String key){
        String value ="";
        try{
            value = jsonObject.has(key)?jsonObject.getString(key):"";
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

}
