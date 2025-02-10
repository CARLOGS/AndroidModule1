package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import com.dgtic.androidmodule1.ejercise.home.vidalruiz.exercise2.RecyclerView.VidalChessEntity

/**
 * VidalChessAdapter is a RecyclerView.Adapter responsible for displaying a list of VidalChessEntity objects.
 * It binds chess opening data to the item views within a RecyclerView.
 */
class VidalChessAdapter(private val openings: List<VidalChessEntity>) : RecyclerView.Adapter<VidalChessAdapter.ChessViewHolder>() {

    /**
     * ChessViewHolder holds references to the ImageView and TextViews
     * representing an opening's image, name, color, and other attributes.
     */
    class ChessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivOpening: ImageView = view.findViewById(R.id.ivOpening)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvColor: TextView = view.findViewById(R.id.tvColor)
        val tvAggressiveness: TextView = view.findViewById(R.id.tvAggressiveness)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvEcoCode: TextView = view.findViewById(R.id.tvEcoCode)

        /**
         * Binds data from VidalChessEntity to the UI components.
         */
        fun render(chessEntity: VidalChessEntity) {
            tvName.text = chessEntity.name
            tvColor.text = "Color: ${chessEntity.color}"
            tvAggressiveness.text = "Aggressiveness: ${chessEntity.aggressiveness}/5"
            tvDescription.text = chessEntity.shortDescription
            tvEcoCode.text = "ECO Code: ${chessEntity.ecoCode}"

            // Si usas imágenes en resources, descomenta la siguiente línea:
            // ivOpening.setImageResource(getImageResource(chessEntity.image))
        }
    }

    /**
     * Called when RecyclerView needs a new ChessViewHolder of the given type to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChessViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chess_opening, parent, false)
        return ChessViewHolder(view)
    }

    /**
     * Returns the total number of items in the dataset held by the adapter.
     */
    override fun getItemCount(): Int = openings.size

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ChessViewHolder, position: Int) {
        holder.render(openings[position])
    }
}
