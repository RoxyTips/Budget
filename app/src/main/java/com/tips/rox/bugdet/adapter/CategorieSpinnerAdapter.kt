package com.tips.rox.bugdet.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tips.rox.bugdet.R
import com.tips.rox.bugdet.data.Categorie
import kotlinx.android.synthetic.main.adapter_spinner_categories.view.*


/**
 * Created by rox on 23/12/2017
 */
class CategorieSpinnerAdapter(context : Context, ressource:Int, list:List<Categorie>) :
        ArrayAdapter<Categorie>(context, ressource, list) {

    var ressource:Int
    var list : List<Categorie>
    var lay : LayoutInflater

    init {
        this.ressource = ressource
        this.list = list
        this.lay = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getPosition(item: Categorie?): Int {
        return super.getPosition(item)
    }

    override fun getContext(): Context {
        return super.getContext()
    }

    override fun getItem(position: Int): Categorie {
        return list.get(position)
    }


    internal class ViewHolder {
        var image: ImageView? = null
        var text : TextView? = null
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val item = getItem(position)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vi: View = inflater.inflate(R.layout.adapter_spinner_categories, null) //convertView as View

        //vi = inflater.inflate(R.layout.adapter_spinner_categories, null)
        val color : ImageView = vi.findViewById(R.id.iv_color_categorie)
        val labelCat : TextView = vi.findViewById(R.id.tv_adapter_categorie)

        color.setBackgroundColor(Color.parseColor("#" + item._couleurCategorie))
        labelCat.text = item._libelleCategorie
        return vi
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return list.get(position)._idCategorie.toLong()
    }

    override fun getCount(): Int {
        return super.getCount()
    }
}