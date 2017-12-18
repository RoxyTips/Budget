package com.tips.rox.bugdet.data

import android.arch.persistence.room.*
import java.util.*

/**
 * Created by rox on 09/12/2017
 */
@Entity(tableName = "Depense", foreignKeys =
                        arrayOf(ForeignKey
                            (entity = Categorie::class,
                            parentColumns = arrayOf("_idCategorie"),
                            childColumns = arrayOf("idCategorie"),
                            onDelete = ForeignKey.CASCADE)),
        indices = arrayOf(Index(value = "idCategorie", unique = true)))
@TypeConverters(Converters::class)
class Depense {
    @PrimaryKey(autoGenerate = true)
    var _idDepense: Int = 0;
    @ColumnInfo(name = "prixDepense")
    var _prixDepense: Double = 0.0;
    @ColumnInfo(name = "libelleDepense")
    var _libelleDepense : String = "";
    @ColumnInfo(name = "dateDepenses")
    var _dateDepense : Date = Date() ;
    @ColumnInfo(name = "idCategorie")
    var _idCategorie : Int = 0;

}
