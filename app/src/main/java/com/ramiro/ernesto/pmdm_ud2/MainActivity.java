package com.ramiro.ernesto.pmdm_ud2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ramiro.ernesto.pmdm_ud2.adapters.CustomListAdapter;
import com.ramiro.ernesto.pmdm_ud2.fragments.CourseDetailFragment;

import java.util.ArrayList;
import java.util.Arrays;

//getting ready for progress bar dialog

public class MainActivity extends Activity {

    // Propiedad de clase pública para identificar los
    // datos enviados en el Intent a otra Activity.
    public static final String KEY_INTENT = "item_position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Indicamos la interfaz de usuario de esta activity
        setContentView(R.layout.activity_main);
        // Recuperamos de la interfaz la lista por su ID, como
        // el método findViewById() devuelve un objeto de tipo View
        // genérico, se hace un cast al tipo que buscamos, en este caso
        // un ListView
        ListView courseList = (ListView) findViewById(R.id.coursesList);

        // Creamos e inicializamos una nueva variable para
        // almacenar la lista de cursos que vamos a mostrar
        // en la lista
        ArrayList<String> courses = new ArrayList<String>();
        // Obtenemos de los recursos de la aplicación los arrays de
        // de los diferentes cursos, necesitamos convertirlos a un
        // tipo lista con Arrays.asList() y lo añadimos a nuestra lista
        // courses
        courses.addAll(Arrays.asList(getResources().getStringArray(R.array.
                cursos_ciclos_formativos)));
        courses.addAll(Arrays.asList(getResources().getStringArray(R.array.
                cursos_formación_especializada_anuales)));
        courses.addAll(Arrays.asList(getResources().getStringArray(R.array.
                cursos_formación_especializada_intensivos)));
        courses.addAll(Arrays.asList(getResources().getStringArray(R.array.
                cursos_esp_online)));

        // Para mostrar datos en un ListView necesitamos crear primero
        // un adaptador que interprete los datos y lo añada a la vista.
        // A este adaptador le pasamos el contexto actual con this, la
        // interfaz donde se va a mostrar (layout) y la lista de elementos

////        adapter no personalizado
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);

// Crear adapte customizado con el layout personalizado
// y datos de los cursos a mostrar
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.
                item_list, courses);

        // Por último, asignamos el adaptador creado a la ListView para
        // que automáticamente se muestren los datos al usuario
        courseList.setAdapter(adapter);

//        listener
        courseList.setOnItemClickListener(new AdapterView.
                OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id)
            {

//                CourseDetailFragment courseDetailFragment =
//                        CourseDetailFragment.newInstance(position);
//                courseDetailFragment.show(getFragmentManager(), "");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(KEY_INTENT, position);
                startActivity(intent);
            }
        });


    }
}
