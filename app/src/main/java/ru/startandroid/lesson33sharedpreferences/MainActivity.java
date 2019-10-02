package ru.startandroid.lesson33sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etText;
    Button btnSave, btnLoad;

    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //нахожу view по id и вешаю listener
        etText = (EditText) findViewById(R.id.etText);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    //при закрытии приложения срабатывает метод saveText
    protected void onDestroy(){
        super.onDestroy();
        saveText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                //при нажатии на btnSave будет выполняться метод saveText
                saveText();
                break;
            case R.id.btnLoad:
                //при нажатии на btnLoad будет выполняться метод loadText
                loadText();
                break;
            default:
                break;
        }
    }

    void saveText() {
        //получаю объект sPref
        //данные будут видны только нашему приложению
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        //указываем константу, и содержимое etText
        ed.putString(SAVED_TEXT, etText.getText().toString());
        //сохраняем данные
        ed.commit();
        //выводми тост об успешном сохранении
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        //получаю объект sPref
        //данные будут видны только нашему приложению
        sPref = getPreferences(MODE_PRIVATE);
        //вывожу сохранённые данные
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        //вовожу тост об успешной загрузке
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

}
