package cl.inacap.puntaarenas.ejemplobd.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ComprasDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="base.db";
    private static final int DB_VERSION=1;

    public ComprasDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTxt="create table PRODUCTOS(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT, CANTIDAD INTEGER, " +
                "UNIDAD TEXT, ESTADO INTEGER);";
        sqLiteDatabase.execSQL(sqlTxt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void ingresarProducto(Producto producto)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("NOMBRE",producto.getNombre());
        valores.put("CANTIDAD",producto.getCantidad());
        valores.put("UNIDAD",producto.getUnidad());
        if(producto.isPendiente())
            valores.put("ESTADO",1);
        else valores.put("ESTADO",0);
        db.insert("PRODUCTOS",null,valores);
    }
    public List<Producto> listaProductos()
    {
        SQLiteDatabase db=getReadableDatabase();
        List<Producto> productos=new ArrayList<>();
        Cursor cursor=db.query("PRODUCTOS",
                new String[]{"NOMBRE","CANTIDAD","UNIDAD","ESTADO"},
                null,null,null,null,
                null);
        cursor.moveToFirst();
        int pendienteInt;
        boolean pendiente=true;
        while (cursor.moveToNext())
        {
            pendienteInt=cursor.getInt(3);
            if(pendienteInt==1) pendiente=true;
            else pendiente=false;
            productos.add(new Producto(cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    pendiente));
        }
        cursor.close();
        db.close();
        return productos;
    }
}