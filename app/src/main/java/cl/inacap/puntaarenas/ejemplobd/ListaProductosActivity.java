package cl.inacap.puntaarenas.ejemplobd;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        //Agregar un Listener para enviar el nombre del producto
        //hacia DetallesActivity
        lista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Recupero el item de la lista que se marco
                        Object o=lista.getItemAtPosition(i);
                        String linea=o.toString();
                        //Obtener el nombre
                        String[] separar=linea.split(":");
                        //Llamar a DetallesActivity
                        Intent intent=new Intent(ListaProductosActivity.this,
                                DetallesActivity.class);
                        intent.putExtra("nombreProducto",separar[0]);
                        startActivityForResult(intent,1);//Va a entregar un resultado
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                cargarLista();
            }
        }
    }
}
