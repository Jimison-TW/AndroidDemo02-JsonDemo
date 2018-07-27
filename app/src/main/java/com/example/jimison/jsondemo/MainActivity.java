package com.example.jimison.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//解析json需匯入
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    String mTxt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.infoTxt);
    }


    public void clickJson01(View view) {
        mTxt = "{\"id\":1,\"name\":\"王力宏\",\"title\":\"歌手\",\"result\":{\"age\":30,\"company\":\"環球\"}}";
        try {
            int id = new JSONObject(mTxt).getInt("id");
            String name = new JSONObject(mTxt).getString("name");
            Toast.makeText(getApplicationContext(), name + " - " + id, Toast.LENGTH_SHORT).show();
            String result = new JSONObject(mTxt).getString("result");
            String company = new JSONObject(result).getString("company");
            int age = new JSONObject(result).getInt("age");
            info.setText(company + " - " + age);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickJson02(View view) {
        mTxt = "{\"id\":2,\"name\":\"蘇打綠\",\"title\":\"歌手\",\"result\":[{\"year\":2010,\"album\":\"小宇宙\"},{\"year\":2011,\"album\":\"大宇宙\"}]}";
        try {
            String result = new JSONObject(mTxt).getString("result");
            StringBuffer sb = new StringBuffer();
            String year01 = new JSONArray(result).getJSONObject(0).getString("year");
            String album01 = new JSONArray(result).getJSONObject(0).getString("album");
            String year02 = new JSONArray(result).getJSONObject(1).getString("year");
            String album02 = new JSONArray(result).getJSONObject(1).getString("album");
            sb.append(year01 + " - " + album01 + "\n");
            sb.append(year02).append("-").append(album02).append("\n");
            info.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickJson03(View view) {
        mTxt = "[{\"year\":2010,\"album\":\"小宇宙\"},{\"year\":2011,\"album\":\"大宇宙\"},{\"year\":2012,\"album\":\"中宇宙\"}]";
        StringBuffer sb = new StringBuffer();
        try {
            JSONArray data = new JSONArray(mTxt);
            for (int i = 0; i < data.length(); i++) {
                String year = data.getJSONObject(i).getString("year");
                String album = data.getJSONObject(i).getString("album");
                sb.append(year).append("-").append(album).append("\n");
            }
            info.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //文字中的雙引號會造成字串的中斷，所以可以先在文件中打完後，再貼到程式內，它會自動變成[\“]的格式
    /*
    {"id":1,"name":"王力宏","title":"歌手","result":{"age":30,"company":"環球"}}
    {"id":2,"name":"蘇打綠","title":"歌手","result":[{"year":2010,"album":"小宇宙"},{"year":2011,"album":"大宇宙"}]}
    [{"year":2010,"album":"小宇宙"},{"year":2011,"album":"大宇宙"},{"year":2012,"album":"中宇宙"}]
     */
}
