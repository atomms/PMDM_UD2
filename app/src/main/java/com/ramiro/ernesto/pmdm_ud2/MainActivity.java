package com.ramiro.ernesto.pmdm_ud2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ramiro.ernesto.pmdm_ud2.adapters.CustomListAdapter;
import com.ramiro.ernesto.pmdm_ud2.fragments.CourseDetailFragment;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends Activity {

    //
//getting ready for progress bar dialog
    private ProgressDialog mProgressDialog;
    private ArrayList<String> courses;
    private ListView courseList;

    // Propiedad de clase pública para identificar los
    // datos enviados en el Intent a otra Activity.
    public static final String KEY_INTENT = "item_position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Indicamos la interfaz de usuario de esta activity
        setContentView(R.layout.activity_main);

        // Inicializar y mostrar el dialogo de progreso
        this.showProgress();

        // Inicializar los datos y vistas de la interfaz
        this.init();

        // Iniciar la carga de datos en un AsyncTask
        (new LoadData()).execute();
    }

    private void showProgress() {
        // Se crea una nueva ventana de carga y se inicializa
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Espera un momento");
        mProgressDialog.setMessage("Cargando datos...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Se fija que no pueda cancelarse hasta que finalice
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }


    private void init() {

        courseList = (ListView) findViewById(R.id.coursesList);

        //        listener
        courseList.setOnItemClickListener(new AdapterView.
                OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {

//                CourseDetailFragment courseDetailFragment =
//                        CourseDetailFragment.newInstance(position);
//                courseDetailFragment.show(getFragmentManager(), "");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(KEY_INTENT, position);
                startActivity(intent);
            }
        });


    }

    private void setAdapter() {

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


    }

    private class LoadData extends AsyncTask<Void, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> courses = new ArrayList<String>();

            try {


                // Recuperamos de la interfaz la lista por su ID, como
                // el método findViewById() devuelve un objeto de tipo View
                // genérico, se hace un cast al tipo que buscamos, en este caso
                // un ListView


                // Creamos e inicializamos una nueva variable para
                // almacenar la lista de cursos que vamos a mostrar
                // en la lista


//        ArrayList<String> courses = new ArrayList<String>();


                // Obtenemos de los recursos de la aplicación los arrays de
                // de los diferentes cursos, necesitamos convertirlos a un
                // tipo lista con Arrays.asList() y lo añadimos a nuestra lista
                // courses
                courses.addAll(Arrays.asList(

                        getResources()

                                .

                                        getStringArray(R.array.
                                                cursos_ciclos_formativos)

                ));
                courses.addAll(Arrays.asList(

                        getResources()

                                .

                                        getStringArray(R.array.
                                                cursos_formación_especializada_anuales)

                ));
                courses.addAll(Arrays.asList(

                        getResources()

                                .

                                        getStringArray(R.array.
                                                cursos_formación_especializada_intensivos)

                ));
                courses.addAll(Arrays.asList(

                        getResources()

                                .

                                        getStringArray(R.array.
                                                cursos_esp_online)

                ));


                // Se detiene la ejecución de la aplicación 2 segundos
                // para que se visualice la ventana de carga
                Thread.sleep(2000);
                            }
            catch (Exception e)
            {
            }
            return courses;
        }

        @Override
        protected void onPostExecute(ArrayList<String> aData)
        {
            super.onPostExecute(aData);

            if(aData.size() > 0)
            {
                // Si se han cargado los datos correctamente se notifia al usuario
                Toast.makeText(MainActivity.this, "Cursos cargados", Toast.LENGTH_SHORT).show();
            }

            // Iinicializamos una nueva variable para
            // almacenar la lista de cursos que vamos a mostrar
            // en la lista y crear el adapter
            courses = aData;
            setAdapter();

            // Ocultar dialogo de progreso
            mProgressDialog.dismiss();
        }


    }
}