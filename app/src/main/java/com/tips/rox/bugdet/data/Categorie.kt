package com.tips.rox.bugdet.data

import android.arch.persistence.room.*

/**
 * Created by rox on 09/12/2017
 */
@Entity(tableName = "Categorie", indices = arrayOf(Index(value = "_idCategorie", unique = true)))
class Categorie {
    @PrimaryKey(autoGenerate = true)
    var _idCategorie:Int=0;
    @ColumnInfo(name = "libelleCategorie")
    var _libelleCategorie:String = "";
    @ColumnInfo(name = "couleurCategorie")
    var _couleurCategorie: String = "";


    fun Categorie(libelleCategorie: String, couleurCategorie: String): Categorie {
        this._libelleCategorie = libelleCategorie
        this._couleurCategorie = couleurCategorie
        return this;
    }

}