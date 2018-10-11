package cl.inacap.puntaarenas.ejemplobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void verLista(View view)
    {
        Intent intent=new Intent(this,
                ListaProductosActivity.class);
        startActivity(intent);
    }
    public void ingresarNuevo(View view)
    {
        Intent intent=new Intent(this,
                NuevoProductoActivity.class);
        startActivity(intent);

    }
    public void eliminarComprados(View view)
    {

    }
}
