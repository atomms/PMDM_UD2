package com.ramiro.ernesto.pmdm_ud2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;

import com.ramiro.ernesto.pmdm_ud2.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ernesto on 27/05/15.
 */
public class CourseDetailFragment extends DialogFragment
{
    // Parámetros de inicialización del fragment
    private static final String ARG_PARAM1 = "positionPressed";
    // Posición pulsada en la lista y
    // listener de la opción seleccionada
    private int mPositionPressed;
    // Título del curso seleccionado
    private String mCourseTitle;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters. *
     * @param positionPressed Parameter 1.
     * @return A new instance of fragment CourseDetailFragment.
     */
    public static CourseDetailFragment newInstance(int positionPressed)
    {
        CourseDetailFragment fragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, positionPressed);
        fragment.setArguments(args);
        return fragment;
    }
    public CourseDetailFragment()
    {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mPositionPressed = getArguments().getInt(ARG_PARAM1);

            // Creamos e inicializamos una nueva variable para
            // almacenar la lista de cursos que vamos a mostrar
            // en la lista
            ArrayList<String> courses = new ArrayList<String>();

            // Obtenemos de los recursos de la aplicación los arrays de
            // de los diferentes cursos, necesitamos convertirlos a un
            // tipo lista con Arrays.asList() y lo añadimos a nuestra lista
            // courses
            courses.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos_ciclos_formativos)));
            courses.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos_formación_especializada_anuales)));
            courses.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos_formación_especializada_intensivos)));
            courses.addAll(Arrays.asList(getResources().getStringArray(R.array.cursos_esp_online)));

            mCourseTitle = courses.get(mPositionPressed);
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                // Icono de la ventana
                .setIcon(R.drawable.app_logo)
                        // Opción si usuario puede ocultar el dialogo
                .setCancelable(false)
                        // Título del dialogo
                .setTitle("Curso ESP!")
                        // Mensaje del dialogo
                .setMessage(mCourseTitle)
                        // Botón respuesta positiva
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Do something else
                    }
                })
                        // Botón respuesta negativa
                .setNegativeButton("Cancelar", new DialogInterface.
                        OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Do something else
                    }
                }).create();
    }
}