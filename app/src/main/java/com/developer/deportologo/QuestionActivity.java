package com.developer.deportologo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.developer.deportologo.model.Conclusiones;
import com.developer.deportologo.model.DataBase;
import com.developer.deportologo.model.Premisas;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    List<Premisas> lsPremisas;
    List<Premisas> lsAcept;
    List<Premisas> lsCancel;
    Premisas actualquestion;
    TextView question;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        lsAcept = new ArrayList<>();
        lsCancel = new ArrayList<>();
        DataBase dataBase = new DataBase(this,"deportologo",null,1);
        db = dataBase.getWritableDatabase();
        if(db != null){
            Double random = Math.random()* (10-1);
            Integer idConclucion = random.intValue();
            String[] params = {idConclucion.toString()};
            Cursor c = db.rawQuery("SELECT CDCONCLUSION,DSCONCLUSION FROM CONCLUSIONES WHERE  CDCONCLUSION = ?",params);
            Conclusiones con = null;
            if (c.moveToFirst()){
                do{
                    con = new Conclusiones();
                    con.setCdconclusiones(c.getInt(0));
                    con.getDsconclusiones(c.getString(1));
                }while (c.moveToNext());
            }
            params = new String[] {con.getCdconclusiones().toString()};

            Cursor rs = db.rawQuery("SELECT P.CDPREMISA, p.DSPREMISA FROM PREMISA P " +
                            "INNER JOIN REGLAS R ON R.CDPREMISA = P.CDPREMISA WHERE CDCONCLUSION = ? ORDER BY P.CDPREMISA"
                    ,params);
            lsPremisas = new ArrayList<>();

            if(rs.moveToFirst()){
                do{
                    Premisas p = new Premisas();
                    p.setCdpremisa(rs.getInt(0));
                    p.setDspremisa(rs.getString(1));
                    lsPremisas.add(p);
                }while (rs.moveToNext());
            }

            actualquestion = lsPremisas.get(0);

            question = (TextView) findViewById(R.id.textview_question);
            question.setText(actualquestion.getDspremisa());
        }
    }




    public void btnyes_AE(View view) {
        lsAcept.add(actualquestion);
        boolean next = false;
        for (Premisas p: lsPremisas) {
            if (next){
                actualquestion = p;
                break;
            }
            if(p.equals(actualquestion)){
                next =  true;
            }
        }


        question.setText(actualquestion.getDspremisa());
    }

    public void btnNot_AE(View view) {
        lsCancel.add(actualquestion);
        String[] params = new String[]{};
        StringBuilder query = new StringBuilder();
        query.append("SELECT CDCONCLUSION FROM  REGLAS WHERE CDPREMISA NOT IN (");
        for (int i= 0; i < lsCancel.size(); i++ ){
            params[i] =  lsCancel.get(i).getCdpremisa().toString();
            query.append("?,");
        }

        query.substring(-1);
        query.append(") GROUP BY CDCONCLUSION");
        Cursor cursor = db.rawQuery(query.toString(),params);
    }

}
