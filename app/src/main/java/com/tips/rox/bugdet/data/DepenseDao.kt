package com.tips.rox.bugdet.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.database.Cursor
import java.util.*

/**
 * Created by rox on 09/12/2017
 */
@Dao
interface DepenseDao {

    @Query("SELECT SUM(d.prixDepense) AS prixDepense " +
            "FROM Depense d " +
            "WHERE dateDepenses BETWEEN :arg0 AND :arg1")
    fun getSumDepenseByMonth(month:Long, year:Long):Int;

    @Query("SELECT c.libelleCategorie, SUM(d.prixDepense), c.couleurCategorie " +
            "FROM Depense d " +
            "INNER JOIN Categorie c ON d.idCategorie = c._idCategorie " +
            "WHERE dateDepenses BETWEEN :arg0 AND :arg1 " +
            "GROUP BY c.libelleCategorie, c.couleurCategorie")
    fun getSumDepenseByCategorie(month:Long, year:Long):Cursor;

   @Insert
   fun saveDepense(depense : Depense)

   @Insert
   fun saveCategorie(categorie: Categorie)

    @Query("SELECT * FROM Categorie")
    fun getCategories() :List<Categorie>

    //@Query("SELECT libelleCategorie FROM Categorie")
    //fun getCategorieNames() : ArrayList<String>

}