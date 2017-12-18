package com.tips.rox.bugdet

import android.app.FragmentManager
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.mikephil.charting.charts.PieChart
import com.tips.rox.bugdet.data.AppDatabase
import com.tips.rox.bugdet.fragments.CategorieFragment
import com.tips.rox.bugdet.fragments.DepenseFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tips.rox.bugdet.data.Depense
import com.tips.rox.bugdet.data.DepenseDao
import java.time.LocalDateTime
import java.util.*


class MainActivity : AppCompatActivity() {
    var db :AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fabAddCategory.setOnClickListener { view ->
            val fragment = CategorieFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment, fragment)
            transaction.commit()
        }

        fabAddDepense.setOnClickListener{view ->
            val fragment = DepenseFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment, fragment)
            transaction.commit()
        }

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "Budget").allowMainThreadQueries().build();

        initializeData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initializeData(){
        val pieChart = findViewById<PieChart>(R.id.chart_by_categorie)
        val entries = ArrayList<PieEntry>()


        val month = Calendar.getInstance().get(Calendar.MONTH)+1
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val depense = db!!.depenseDao().getSumDepenseByMonth(month, year)
        val restant: Int = 2400 - depense
        entries.add(PieEntry(restant.toFloat(), "Restant"))
        entries.add(PieEntry(depense.toFloat(), "Dépenses"))

        val set = PieDataSet(entries, "Election Results")
        val data = PieData(set)
        pieChart.setData(data)
        pieChart.invalidate() // refresh
    }
}
