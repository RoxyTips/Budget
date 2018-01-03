package com.tips.rox.bugdet

import android.app.Application
import android.app.FragmentManager
import android.arch.persistence.room.Room
import android.database.Cursor
import android.graphics.Color
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
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.time.temporal.TemporalQueries.localDate
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var db :AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fabAddCategory.setOnClickListener { view ->
            val fragment = CategorieFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.add(R.id.fragment, fragment)
            transaction.commit()
        }

        fabAddDepense.setOnClickListener{view ->
            val fragment = DepenseFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.add(R.id.fragment, fragment)
            transaction.commit()
        }

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "Budget").allowMainThreadQueries().build();

        initializeData()

        initializeCategoriesData()
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
        val pieChart = findViewById<PieChart>(R.id.chart_global)
        val entries = ArrayList<PieEntry>()
        pieChart.description.text = "";


        val month = Calendar.getInstance().get(Calendar.MONTH)+1
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val c : Calendar = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH, 1)

        val firstDayOfMonth : Long = c.timeInMillis

        val today : Long = Calendar.getInstance().timeInMillis
        //val localdate : LocalDate  = LocalDate.now()
        //val lastOfMonth = localdate.with(TemporalAdjusters.lastDayOfMonth())
        //val timestamp = Timestamp(localdate.getLong())
        //val timeEnd : Timestamp = lastOfMonth
        val depense = db!!.depenseDao().getSumDepenseByMonth(firstDayOfMonth, today)
        val restant: Int = 2400 - depense

        entries.add(PieEntry(restant.toFloat(), "Restant"))
        entries.add(PieEntry(depense.toFloat(), "Dépenses"))


        val set = PieDataSet(entries, "Dépenses du mois")
        val colors = ArrayList<Int>()
        colors.add(getColor(R.color.vert))
        colors.add(getColor(R.color.rouge))

        set.colors =  colors
        val data = PieData(set)
        pieChart.setData(data)
        pieChart.invalidate() // refresh
    }

    fun initializeCategoriesData(){
        val pieChart = findViewById<PieChart>(R.id.chart_by_categorie)
        pieChart.description.text = "";
        val entries = ArrayList<PieEntry>()

        val c : Calendar = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth : Long = c.timeInMillis
        val today : Long = Calendar.getInstance().timeInMillis
        val depenses = db!!.depenseDao().getSumDepenseByCategorie(firstDayOfMonth, today)

        val list = mutableMapOf<String, Int>()
        val colors = ArrayList<Int>()
        //var list : Dictionary<String, Int>
        depenses.moveToFirst()
        while (!depenses.isAfterLast) {
            entries.add(PieEntry(depenses.getFloat(1), depenses.getString(0)))
            colors.add(Color.parseColor("#" + depenses.getString(2)))
            depenses.moveToNext()
        }
        val set = PieDataSet(entries, "Dépenses du mois par catégorie")


        set.colors =  colors
        val data = PieData(set)
        pieChart.setData(data)
        pieChart.invalidate() // refresh
    }


}
