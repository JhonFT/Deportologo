package com.developer.deportologo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Developer on 20/11/2016.
 */

public class DataBase extends SQLiteOpenHelper {
    String tbPremisas = "CREATE TABLE PREMISA (CDPREMISA INTEGER PRIMARY KEY, DSPREMISA VARCHAR(100))";
    String tbConclusiones  = "CREATE TABLE CONCLUSIONES ( CDCONCLUSION INTEGER PRIMARY KEY, DSCONCLUSION VARCHAR(100), DSIMAGEN TEXT)";
    String tbReglas  = "CREATE TABLE REGLAS (CDREGLA INTEGER PRIMARY KEY AUTOINCREMENT,CDPREMISA INTEGER ,CDCONCLUSION INTEGER)";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //si no existe la base de datos
        db.execSQL(tbPremisas);
        db.execSQL(tbConclusiones);
        db.execSQL(tbReglas);

        db.execSQL("INSERT INTO CONCLUSIONES(CDCONCLUSION,DSCONCLUSION) VALUES(1,'NATACIÓN')," +
                "(2,'GIMNASIA'),(3,'PATINAJE'),(4,'FUTBOL'),(5,'VOLEIBOL'),(6,'CICLISMO'),(7,'BOXEADOR'),(8,'TENIS'),(9,'ATLETISMO'),(10,'BALONCESTO')");

        db.execSQL("INSERT INTO PREMISA(CDPREMISA,DSPREMISA) VALUES(1,'¿Tiene motivación?')," +
                "(2,'¿movilidad articular?'),(3,'¿agilidad mental?'),(4,'resistencia?'),(5,'¿tiene coordinación?'),(6,'¿tiene velocidad?'),(7,'¿tiene disciplina?')," +
                "(8,'¿tiene actitud?'),(9,'¿tiene capacidad rítmica?'),(10,'¿tiene fuerza?'),(11,'¿tiene flexibilidad?'),(12,'¿tiene rapidez?')," +
                "(13,'¿tiene fuerza en los brazos?'),(14,'¿tiene agilidad?'), (15,'¿tiene alta capacidad de respiración bajo el agua?'),(16,'¿Es delgado?')," +
                "(17,'¿tiene equilibrio?'),(18,'¿tiene potencia?'),(19,'¿tiene habilidad para saltar?'),(20,'¿tiene fuerza en las piernas?'),(21,'¿Es acuerpado?')," +
                "(22,'¿tiene fuerza en los brazos?'),(23,'¿tiene piernas largas?'),(24,'¿mide más de 1,85 metros?'),(25,'¿tiene habilidad con el balón en los pies?')");

        db.execSQL("INSERT INTO REGLAS(CDCONCLUSION,CDPREMISA) VALUES (1,2),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,11),(1,12),(2,1),(2,2),(2,3),(2,4),(2,5)," +
                "(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,14),(2,16),(3,3),(3,4),(3,5),(3,6),(3,8),(3,9),(3,11),(3,12),(3,17),(4,3),(4,4),(4,5),(4,6)," +
                "(4,8),(4,9),(4,10),(4,12),(4,14),(4,18),(4,25),(5,3),(5,4),(5,5),(5,6),(5,9),(5,11),(5,12),(5,18),(5,19),(6,1),(6,2),(6,4),(6,6),(6,12),(6,18)," +
                "(6,20),(7,1),(7,3),(7,4),(7,5),(7,6),(7,7),(7,8),(7,10),(7,12),(7,18),(7,21),(8,3),(8,4),(8,5),(8,6),(8,11),(8,12),(8,13),(8,14),(8,18),(8,22)," +
                "(9,1),(9,2),(9,4),(9,5),(9,6),(9,7),(9,10),(9,11),(9,12),(9,14),(9,18),(9,23),(10,2),(10,3),(10,4),(10,5),(10,8),(10,10),(10,11),(10,12),(10,14),(10,24)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // actualizar base de datos
        db.execSQL("DROP TABLE IF EXISTS PREMISA");
        db.execSQL("DROP TABLE IF CONCULSIONES PREMISA");
        db.execSQL("DROP TABLE IF CDREGLA PREMISA");

        db.execSQL(tbPremisas);
        db.execSQL(tbConclusiones);
        db.execSQL(tbReglas);
    }
}
