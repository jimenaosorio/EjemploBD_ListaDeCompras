package cl.inacap.puntaarenas.ejemplobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.puntaarenas.ejemplobd.modelo.ComprasDatabaseHelper;
import cl.inacap.puntaarenas.ejemplobd.modelo.Producto;

public class DetallesActivity extends AppCompatActivity {

    public Producto producto;
    public Intent intent;
    public ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //Obtener el producto
        intent=getIntent();
        String nombreProducto=(String)intent.getExtras().get("nombreProducto");

        //Traer el producto desde la base de datos
        producto=helper.getProducto(nombreProducto);

        //Mostrar la informacion del producto
        TextView txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtNombre.setText(producto.getNombre());
        TextView txtUnidad=(TextView)findViewById(R.id.txtUnidad);
        txtUnidad.setText(producto.getCantidad()+" "+producto.getUnidad());
        TextView txtEstado=(TextView)findViewById(R.id.txtEstado);
        Button cambiar=(Button)findViewById(R.id.estado);
        if(producto.isPendiente()){
            txtEstado.setText("Pendiente");
            cambiar.setText("Marcar como comprado");
        }
        else{
            txtEstado.setText("Comprado");
            cambiar.setText("Marcar como pendiente");
        }

    }
    public void cambiarEstado(View view)
    {
        producto.setPendiente(!producto.isPendiente());
        //Actualizar en la base de datos
        String mensaje=helper.cambiarEstado(producto);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK,intent); //Enviar el resultado
        finish();
    }


}
