package com.example.projetointegrador

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.adapters.RotasAdapter
import com.example.projetointegrador.models.DistanciaMap
import com.example.projetointegrador.models.RotaMinima
import com.example.projetointegrador.repository.Repository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    private var distancias: MutableList<DistanciaMap> = mutableListOf()
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RotasAdapter? = null
    private val fragmentManager = supportFragmentManager
    var dialogFragment: RotasMinFragment? = null
    private val READ_REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        solve_problem_button.setOnClickListener {
            resolve()
        }
        getFile.setOnClickListener { openDocumetosSearch() }
    }

    private fun openResultFragment(rotas: MutableList<RotaMinima>) {
        val ft = fragmentManager.beginTransaction()
        ft.addToBackStack(null)
        dialogFragment = RotasMinFragment(rotas)
        dialogFragment?.show(ft, "Opções")
    }

    private fun populateView() {
        rotas_recycler_view.visibility = View.VISIBLE
        solve_problem_button.visibility = View.VISIBLE
        getFile.visibility = View.INVISIBLE
        recyclerView = rotas_recycler_view
        recyclerViewAdapter = RotasAdapter(distancias)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.layoutManager = GridLayoutManager(this, 1)
        desabilitarCarregando()
    }

    private fun resolve() {
        habilitarCarregando()
        Repository.solveDijkstra(cidadeOrigem = "A", rotas = this.distancias,
            sucess = { response ->
                desabilitarCarregando()
                if (response != null)
                    openResultFragment(response)
            },
            error = {
                desabilitarCarregando()
                showToast(it)
            }
        )
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun habilitarCarregando() {
        solve_problem_button.visibility = View.INVISIBLE
        progressbar_holder.visibility = View.VISIBLE
    }


    private fun desabilitarCarregando() {
        solve_problem_button.visibility = View.VISIBLE
        progressbar_holder.visibility = View.INVISIBLE
    }

    private fun openDocumetosSearch() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data.also { uri ->
                habilitarCarregando()
                val fileContet = readTextFromUri(uri!!)
                distancias = Gson()
                    .fromJson(fileContet, Array<DistanciaMap>::class.java)
                    .toMutableList()
                populateView()
            }
        }
    }

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                }
            }
        }
        return stringBuilder.toString()
    }

}
