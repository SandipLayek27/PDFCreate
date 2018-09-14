# PDFCreate
It is a intelligent library system where most immportant inputed field is JSONArray it atometically detect Header names and re-arrange those names with particular valid data which hold JSONObject data.
JSONArray to PDF (.pdf) export system [Normally PDF or Password Protected PDF create as you want].

Here, You can also pass normal string formated value which can automatically export to pdf format(.pdf) 
[Normally PDF or Password Protected PDF create as you want].

We create this library for my working purpose.
Here, we used some pre generated libraries and modify it's working process.

## Developed
[![Sandip](https://avatars1.githubusercontent.com/u/31722942?v=4&u=18643bfaaba26114584d27693e9891db26bcb582&s=39) Sandip](https://github.com/SandipLayek27)  
# ★ Gradle Dependency
Add Gradle dependency in the build.gradle file of your application module (app in the most cases) :
First Tab:

```sh
allprojects{
    repositories{
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

AND

```sh
dependencies {
    implementation 'com.github.SandipLayek27:PDFCreate:1.0'
}
```

# ★ Features are
1. String to PDF EXPORT(.pdf format)Normally.
2. String to PDF EXPORT(.pdf format)With PASSWORD Protected.
3. JSONArray to PDF EXPORT(.pdf format)Normally.
4. JSONArray to PDF EXPORT(.pdf format)With PASSWORD Protected.

# ★ Uses
```sh
1. String to PDF EXPORT(.pdf format)Normally.
2. String to PDF EXPORT(.pdf format)With PASSWORD Protected.
3. JSONArray to PDF EXPORT(.pdf format)Normally.
4. JSONArray to PDF EXPORT(.pdf format)With PASSWORD Protected.
❆ 1 PERMISSIONS:-
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

❆ 2 CODE:-
        String paragraph ="Hello My Name is Sandip Layek";  /
        String title ="MyName";   //PDF FILE NAME
        String password = "123";
        
        1. String to PDF EXPORT(.pdf format)Normally.
          CreatePdfNormally createPdfNormally = new CreatePdfNormally(MainActivity.this);
          createPdfNormally.create(paragraph,title);
        
        2. String to PDF EXPORT(.pdf format)With PASSWORD Protected.
          CreatePdfPassword createPdfPassword = new CreatePdfPassword(MainActivity.this);
          createPdfPassword.create(paragraph,title,password);
        
        3. JSONArray to PDF EXPORT(.pdf format)Normally.
          CreatePdfNormally createPdfNormally = new CreatePdfNormally(MainActivity.this);
          createPdfNormally.createJSONArrayToPDF(jsonArray,title);
        
        4. JSONArray to PDF EXPORT(.pdf format)With PASSWORD Protected.
          CreatePdfPassword createPdfPassword = new CreatePdfPassword(MainActivity.this);
          createPdfPassword.createJSONArrayToPDF(jsonArray,title,password);

        
❆ 2 NOTES:-
Here we already paste a json formated file to my project asset folder as example purpose.
// Download .pdf file to your internal download folder.        
```

