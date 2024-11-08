package centeno.home.guiacincoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import centeno.home.guiacincoapp.Entidades.Personas;


public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Personas> listaPersonas;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    // Interfaces para manejar los eventos de clic y clic largo
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    // Método para establecer la lista de personas
    public void setPersonasList(List<Personas> personas) {
        this.listaPersonas = personas;
        notifyDataSetChanged(); // Refrescar la vista cuando cambia la lista
    }

    // Métodos para asignar los listeners
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    // Método para obtener una persona en una posición específica
    public Personas getPersonaAt(int position) {
        return listaPersonas.get(position);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de la lista
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        // Obtener la persona en la posición actual
        Personas persona = listaPersonas.get(position);

        // Asignar valores a los TextViews
        holder.tvNombre.setText("Nombre: " + persona.nombrePersona);
        holder.tvApellido.setText("Apellido: " + persona.apellidoPersona);
        holder.tvEdad.setText("Edad: " + persona.edadPersona);

        // Manejar clic en el elemento para editar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (clickListener != null && position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position);
                }
            }
        });

        // Manejar clic largo en el elemento para eliminar
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                    longClickListener.onItemLongClick(position);
                    return true;
                }
                return false;
            }
        });
    }

    // Contar la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return listaPersonas != null ? listaPersonas.size() : 0;
    }

    // ViewHolder para manejar los elementos del RecyclerView
    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvApellido;
        TextView tvEdad;

        PersonaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.etNombre);
            tvApellido = itemView.findViewById(R.id.etApellido);
            tvEdad = itemView.findViewById(R.id.etEdad);
        }
    }
}
