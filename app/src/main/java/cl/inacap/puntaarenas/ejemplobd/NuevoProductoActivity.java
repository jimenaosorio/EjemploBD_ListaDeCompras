package cl.inacap.puntaarenas.ejemplobd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.puntaarenas.ejemplobd.modelo.ComprasDatabaseHelper;
import cl.inacap.puntaarenas.ejemplobd.modelo.Producto;

public class NuevoProductoActivity extends AppCompatActivity {

    private ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
    }

    public void ingresarProducto(View view)
    {
        String nombre=
                ((TextView)findViewById(R.id.ingresarNombre)).getText().toString();
        String cantidadStr=
                ((TextView)findViewById(R.id.ingresarCantidad)).getText().toString();
        String unidad=
                ((Spinner)findViewById(R.id.ingresarUnidad)).getSelectedItem().toString();
        String unidadNueva=
                ((TextView)findViewById(R.id.otraUnidad)).getText().toString();
        int cantidad=0;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        }catch (NumberFormatException e){
            Toast.makeText(this,"Debe ingresar un numero en la cantidad",
                    Toast.LENGTH_SHORT).show();
        }
        if(!nombre.isEmpty() && cantidad>0){
            if(unidad.equals("Otro")){
                unidad=unidadNueva;
            }
            //Creamos el objeto Producto
            Producto producto=new Producto(nombre,cantidad,unidad);
            //Ingresamos el producto a la base de datos
            helper.ingresarProducto(producto);

            Toast.makeText(this,"Producto ingresado",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
