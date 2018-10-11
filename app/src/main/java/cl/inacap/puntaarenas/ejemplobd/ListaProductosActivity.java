package cl.inacap.puntaarenas.ejemplobd;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.puntaarenas.ejemplobd.modelo.ComprasDatabaseHelper;
import cl.inacap.puntaarenas.ejemplobd.modelo.Producto;

public class ListaProductosActivity extends ListActivity {

    private ListView lista;
    ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }
    public void cargarLista()
    {
        lista=getListView();

        //Leer la lista desde la base de datos
        List<Producto> productoList=
                (ArrayList<Producto>)helper.listaProductos();
        //Hacer un adaptador
        ArrayAdapter<Producto> listaAdapter=
                new ArrayAdapter<Producto>(this,
                        android.R.layout.simple_list_item_1,
                        productoList);
        //Agregar el adaptador al ListView
        lista.setAdapter(listaAdapter);
    }
}
