package com.ramiro.ernesto.pmdm_ud2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramiro.ernesto.pmdm_ud2.R;

import java.util.List;

/**
 * Created by ernesto on 2/06/15.
 */
public class CustomListAdapter extends ArrayAdapter<String>
{
    public CustomListAdapter (Context context, int resource, List<String>
            items)
    {
// Crear el adapter llamando al método
// del padre con los datos proporcionados
        super(context, resource, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
// Si el la vista o el tag almacenado en la lista no
// no es una instancia de la clase ViewHolder, es necesario
// cargar el layout y obtener los componentes de la interfaz
        if (convertView == null || !(convertView.getTag() instanceof
                ViewHolder))
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.
                    id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
// Se asigna el objeto viewHolder a la vista para
// poder reutilizar las vistas en las de más filas
            convertView.setTag(viewHolder);
        }
        else
        {
// Si ya se había guardado el viewHolder en el tag
// de la vista de la fila, se recupera para evitar
// llamar al método findViewById()
            viewHolder = (ViewHolder) convertView.getTag();
        }
// Se asignan los valores a los componentes de la interfaz
// en función de la fila seleccionada
        viewHolder.image.setImageResource(R.drawable.app_logo);
        viewHolder.title.setText(getItem(position));
        return convertView;
    }
    // Patron de ViewHolder, permite reutilizar
// las vistas en las listas. De esta manera
// se reducen las llamadas al método findViewById()
// lo que aumenta la eficiencia de la listas
    private static class ViewHolder
    {
        ImageView image;
        TextView title;
    }
}

