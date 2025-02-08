package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R

/**
 * AnimalAdapter is a RecyclerView.Adapter responsible for displaying a list of AnimalEntity objects.
 * It provides the necessary methods to bind animal data to the item views within a RecyclerView.
 */
class AnimalAdapter (private val animals: List<AnimalEntity>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    /**
     *  [AnimalViewHolder] is a custom ViewHolder for displaying [AnimalEntity] objects in a RecyclerView.
     *
     *  This ViewHolder holds references to the ImageView and TextViews that represent an animal's image, name, and color in the item layout.
     *
     *  @param view The root [View] of the item layout that this ViewHolder will manage.
     *///Nuestra implementación de ViewHolder.
    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        //Obtenemos los elementos de la vista
        val ivAnimal: ImageView = view.findViewById(R.id.ivAnimal);
        val tvName: TextView = view.findViewById(R.id.tvName);
        val tvColor: TextView = view.findViewById(R.id.tvColor);

        //funcion para imprimir los resultados.
        fun render(animalEntity: AnimalEntity)
        {
            tvName.text = animalEntity.name;
            tvColor.text = animalEntity.color;
            //ivAnimal.setImageResource(getImage(animalEntity.image));
        }
    }

    /**
     * Called when RecyclerView needs a new {@link AnimalViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(AnimalViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     *
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(AnimalViewHolder, int)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false);
        return AnimalViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * This method is called by the RecyclerView to determine the number of items that
     * the adapter should display. It's crucial for the RecyclerView to properly
     * render the list of data.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return animals.count();
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [AnimalViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * This implementation checks if the `animals` list is not empty and then calls the `render`
     * function of the provided [AnimalViewHolder] to bind the data from the `animals` list at the
     * given `position` to the ViewHolder's views.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
            holder.render(animals[position]);
    }

}