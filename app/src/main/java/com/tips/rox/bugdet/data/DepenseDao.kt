package com.tips.rox.bugdet.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by rox on 09/12/2017
 */
@Dao
interface DepenseDao {

    @Query("SELECT SUM(d.prixDepense) AS prixDepense " +
            "FROM Depense d " +
            "WHERE strftime('%m', dateDepenses) = :arg0 " +
            "AND strftime('%y', dateDepenses) = :arg1 ")
    fun getSumDepenseByMonth(month:Int, year:Int):Int;


   @Insert
   fun saveDepense(depense : Depense)

   @Insert
   fun saveCategorie(categorie: Categorie)


}