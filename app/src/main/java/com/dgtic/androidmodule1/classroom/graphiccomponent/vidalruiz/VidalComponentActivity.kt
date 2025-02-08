package com.dgtic.androidmodule1.classroom.graphiccomponent.vidalruiz

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgtic.androidmodule1.R
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class VidalComponentActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView;
    lateinit var etUsername: EditText;
    lateinit var btnSend : Button
    lateinit var btnImageButton : ImageButton
    lateinit var chkExample : CheckBox;
    lateinit var radGroup : RadioGroup;
    lateinit var rbMale : RadioButton;
    lateinit var rbFemale : RadioButton;
    lateinit var spExample : Spinner;

    //Un evento para obtener los clicks sobre componentes.
    private val clickListener = View.OnClickListener {viewClicked ->
        when (viewClicked.id) {
            R.id.btnSend -> {
                val selectedId = radGroup.checkedRadioButtonId;
                val radioButton = findViewById<RadioButton>(selectedId);
                Toast.makeText(this, "Selected: ${radioButton.text}", Toast.LENGTH_SHORT).show();

                val selectedItem = spExample.selectedItem.toString();
                Toast.makeText(this, "Selected: $selectedItem", Toast.LENGTH_SHORT).show();

            }
            R.id.tvTitle -> {
                Toast.makeText(this, "Click on tvTitle", Toast.LENGTH_SHORT).show()
            }
            R.id.btnImageButton  -> {
                Toast.makeText(this, "Click on Image Button", Toast.LENGTH_SHORT).show()
            }
            R.id.imgVer -> {
                Toast.makeText(this, "Click on imgVer", Toast.LENGTH_SHORT).show()
            }
            R.id.chkExample -> {
                Toast.makeText(this, "Click on Chkbox", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Cargamos los datos en el Spinner
    private fun loadData(){
        var data = arrayListOf<String>("Select an option","México","Colombia","Argentina","Chile","Peru");
        var adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,data).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        };
        spExample.adapter = adapter;
        radGroup.check(R.id.rbMale);
        tvTitle.text = "Component Activity.";
    }
    //Ligamos los componentes de la vsita a objetos.
    private fun initView() {
        //Obtengo los elementos de la vista
        tvTitle = this.findViewById<TextView>(R.id.tvTitle);
        etUsername = this.findViewById<EditText>(R.id.etUsername);
        chkExample = this.findViewById<CheckBox>(R.id.chkExample);
        btnSend=this.findViewById<Button>(R.id.btnSend);
        radGroup = this.findViewById<RadioGroup>(R.id.rgGender);
        btnImageButton = this.findViewById<ImageButton>(R.id.btnImageButton);
        rbMale = this.findViewById<RadioButton>(R.id.rbMale);
        rbFemale = this.findViewById<RadioButton>(R.id.rbFemale);
        spExample = this.findViewById<Spinner>(R.id.spExample);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vidal_component)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView();
        loadData();

        //declaro eventos:
        btnImageButton.setOnClickListener(clickListener);
        btnSend.setOnClickListener(clickListener);
        chkExample.setOnClickListener(clickListener);
        etUsername.setOnClickListener(clickListener);
        tvTitle.setOnClickListener(clickListener);

        //Creo un evento para el Spinner Changed.
        setupSpinnerListener(this);
        //Creo un evento para el RadioGroup Changed.
        setupRadioGroupListener(this);
        /*
        chkExample.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Estas de acuerdo: $isChecked", Toast.LENGTH_SHORT).show()
        }
        btnSend.setOnClickListener{
            Toast.makeText(this, "Estas de acuerdo: ${chkExample.isChecked}", Toast.LENGTH_SHORT).show()
        }
        */
    }

    // 1. Create the listener for the Spinner.
    private fun setupSpinnerListener(context: VidalComponentActivity) {
        // 2. Set an OnItemSelectedListener
        spExample.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // 3. Implement the onItemSelected() method
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected item
                val selectedItem = parent?.getItemAtPosition(position).toString()

                // Create and show the Toast message
                Toast.makeText(context, "Selected: $selectedItem", Toast.LENGTH_SHORT).show();
            }

            // 4. Implement the onNothingSelected() method
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // You can add a Toast here if you want to notify the user that nothing is selected
                // Toast.makeText(this@MyActivity, "Nothing selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRadioGroupListener(context: VidalComponentActivity) {
        radGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMale -> {
                    Toast.makeText(context, "Male", Toast.LENGTH_SHORT).show()
                }

                R.id.rbFemale -> {
                    Toast.makeText(context, "Female", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(context, "No selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}