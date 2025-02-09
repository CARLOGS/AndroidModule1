package com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R

class UIComponentAdapterCS(
    private val components: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<UIComponentAdapterCS.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textComponent: TextView = view.findViewById(R.id.textComponent)
        val iconComponent: ImageView = view.findViewById(R.id.iconComponent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ui_componentcs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val component = components[position]
        holder.textComponent.text = component

        // Asignar icono dependiendo del componente
        val iconRes = when (component) {
            "Button" -> R.drawable.ic_launcher_foreground  // En lugar de ic_button
            "TextView" -> R.drawable.ic_launcher_foreground
            "ImageView" -> R.drawable.ic_launcher_foreground
            "EditText" -> R.drawable.ic_launcher_foreground
            "CheckBox" -> R.drawable.ic_launcher_foreground
            "RadioButton" -> R.drawable.ic_launcher_foreground
            "Spinner" -> R.drawable.ic_launcher_foreground
            else -> R.drawable.ic_launcher_foreground
        }

        holder.iconComponent.setImageResource(iconRes)

        // Click en el item
        holder.itemView.setOnClickListener { onClick(component) }
    }

    override fun getItemCount() = components.size
}
