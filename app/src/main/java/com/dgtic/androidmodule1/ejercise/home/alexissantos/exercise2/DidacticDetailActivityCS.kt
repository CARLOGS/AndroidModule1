package com.dgtic.androidmodule1.ejercise.home.alexissantos.exercise2

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgtic.androidmodule1.R
import kotlin.random.Random

class DidacticDetailActivityCS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_didactic_detail_cs)

        val textView = findViewById<TextView>(R.id.textViewDetail)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        val container = findViewById<LinearLayout>(R.id.componentContainer)

        val componentName = intent.getStringExtra("COMPONENT_NAME") ?: "Desconocido"
        textView.text = "Ejemplo del componente: $componentName"

        // Descripción del componente
        val description = when (componentName) {
            "Button" -> "Permite ejecutar acciones al presionar."
            "TextView" -> "Muestra texto en la pantalla."
            "ImageView" -> "Muestra imágenes en la pantalla."
            "EditText" -> "Campo de entrada para escribir texto."
            "CheckBox" -> "Permite seleccionar o deseleccionar opciones."
            "RadioButton" -> "Permite seleccionar solo una opción dentro de un grupo."
            "Spinner" -> "Menú desplegable con múltiples opciones."
            "RecyclerView" -> "Lista eficiente para mostrar grandes cantidades de datos."
            "FrameLayout" -> "Contenedor simple para agrupar elementos."
            "LinearLayout" -> "Organiza elementos en filas o columnas."
            "RelativeLayout" -> "Posiciona elementos en relación con otros."
            "ConstraintLayout" -> "Organiza elementos con restricciones flexibles."
            else -> "Este es un componente de Android."
        }

        textViewDescription.text = description

        // Agregar el componente dinámicamente
        val componentView: View? = when (componentName) {
            "Button" -> createInteractiveButton()
            "TextView" -> createStyledTextView()
            "ImageView" -> createImageView()
            "EditText" -> createValidatedEditText()
            "CheckBox" -> createCheckBox()
            "RadioButton" -> createRadioButton()
            "Spinner" -> createInteractiveSpinner()
            "RecyclerView" -> createExampleRecyclerView()
            "FrameLayout" -> createExampleFrameLayout()
            "LinearLayout" -> createExampleLinearLayout()
            "RelativeLayout" -> createExampleRelativeLayout()
            "ConstraintLayout" -> createExampleConstraintLayout()
            else -> null
        }

        componentView?.let { container.addView(it) }
    }

    private fun createInteractiveButton(): Button {
        return Button(this).apply {
            text = "¡Presióname!"
            setBackgroundColor(Color.BLUE)
            setTextColor(Color.WHITE)
            setOnClickListener {
                setBackgroundColor(getRandomColor())
                Toast.makeText(context, "Color cambiado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createStyledTextView(): TextView {
        return TextView(this).apply {
            text = "Este es un TextView dinámico con estilo"
            textSize = 22f
            setTextColor(Color.MAGENTA)
        }
    }

    private fun createImageView(): ImageView {
        return ImageView(this).apply {
            setImageResource(R.drawable.ic_launcher_foreground)
            layoutParams = LinearLayout.LayoutParams(250, 250)
        }
    }

    private fun createValidatedEditText(): LinearLayout {
        val editText = EditText(this)
        editText.hint = "Escribe algo aquí"

        val button = Button(this).apply {
            text = "Validar"
            setOnClickListener {
                if (editText.text.length < 5) {
                    Toast.makeText(context, "Debe escribir al menos 5 caracteres", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Texto válido: ${editText.text}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(editText)
            addView(button)
        }
    }

    private fun createCheckBox(): CheckBox {
        return CheckBox(this).apply {
            text = "Acepto los términos"
            setOnCheckedChangeListener { _, isChecked ->
                Toast.makeText(context, if (isChecked) "Marcado" else "Desmarcado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createRadioButton(): RadioButton {
        return RadioButton(this).apply {
            text = "Opción 1"
        }
    }

    private fun createInteractiveSpinner(): Spinner {
        val spinner = Spinner(this)
        val items = listOf("Opción A", "Opción B", "Opción C")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@DidacticDetailActivityCS, "Seleccionaste: ${items[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return spinner
    }

    private fun createExampleRecyclerView(): RecyclerView {
        val recyclerView = RecyclerView(this)
        recyclerView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400
        )
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf("Elemento 1", "Elemento 2", "Elemento 3")
        val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val textView = TextView(parent.context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    textSize = 18f
                    text = "Elemento"
                    setPadding(16, 16, 16, 16)
                }
                return object : RecyclerView.ViewHolder(textView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                (holder.itemView as TextView).text = items[position]
            }

            override fun getItemCount() = items.size
        }
        recyclerView.adapter = adapter
        return recyclerView
    }

    private fun getRandomColor(): Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }
    private fun createExampleFrameLayout(): FrameLayout {
        val frameLayout = FrameLayout(this)
        frameLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400
        )
        frameLayout.setBackgroundColor(Color.LTGRAY)

        val textView = TextView(this).apply {
            text = "Este es un FrameLayout"
            textSize = 18f
            setTextColor(Color.BLACK)
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }
        frameLayout.addView(textView)
        return frameLayout
    }

    private fun createExampleLinearLayout(): LinearLayout {
        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400
        )
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setBackgroundColor(Color.CYAN)

        val textView = TextView(this).apply {
            text = "Este es un LinearLayout"
            textSize = 18f
            setTextColor(Color.BLACK)
        }

        val button = Button(this).apply {
            text = "Botón en LinearLayout"
        }

        linearLayout.addView(textView)
        linearLayout.addView(button)
        return linearLayout
    }
    private fun createExampleRelativeLayout(): RelativeLayout {
        val relativeLayout = RelativeLayout(this)
        relativeLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400
        )
        relativeLayout.setBackgroundColor(Color.MAGENTA)

        val textView = TextView(this).apply {
            text = "RelativeLayout"
            textSize = 18f
            setTextColor(Color.WHITE)
            id = View.generateViewId()
        }

        val button = Button(this).apply {
            text = "Botón"
            id = View.generateViewId()
        }

        val paramsTextView = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        paramsTextView.addRule(RelativeLayout.CENTER_HORIZONTAL)
        textView.layoutParams = paramsTextView

        val paramsButton = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        paramsButton.addRule(RelativeLayout.BELOW, textView.id)
        paramsButton.addRule(RelativeLayout.CENTER_HORIZONTAL)
        button.layoutParams = paramsButton

        relativeLayout.addView(textView)
        relativeLayout.addView(button)
        return relativeLayout
    }
    private fun createExampleConstraintLayout(): ConstraintLayout {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400
        )
        constraintLayout.setBackgroundColor(Color.YELLOW)

        val textView = TextView(this).apply {
            text = "ConstraintLayout"
            textSize = 18f
            setTextColor(Color.BLACK)
            id = View.generateViewId()
        }

        val button = Button(this).apply {
            text = "Botón en ConstraintLayout"
            id = View.generateViewId()
        }

        constraintLayout.addView(textView)
        constraintLayout.addView(button)

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 50)
        constraintSet.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 50)

        constraintSet.connect(button.id, ConstraintSet.TOP, textView.id, ConstraintSet.BOTTOM, 50)
        constraintSet.connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 50)

        constraintSet.applyTo(constraintLayout)
        return constraintLayout
    }




}
