package com.tips.rox.bugdet.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.tips.rox.bugdet.BudgetApplication

import com.tips.rox.bugdet.R
import com.tips.rox.bugdet.adapter.CategorieSpinnerAdapter
import com.tips.rox.bugdet.data.Categorie
import com.tips.rox.bugdet.data.Depense
import kotlinx.android.synthetic.main.fragment_depense.*
import java.time.LocalDateTime
import java.util.*
import java.time.ZoneId.systemDefault



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DepenseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DepenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DepenseFragment : Fragment(),AdapterView.OnItemSelectedListener {


    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null


    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //var catNames : ArrayList<String> = BudgetApplication.db!!.depenseDao().getCategorieNames()
        //val arrayAdapter = ArrayAdapter(activity.applicationContext, R.layout.support_simple_spinner_dropdown_item, cat)
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        return inflater!!.inflate(R.layout.fragment_depense, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cat : List<Categorie> = BudgetApplication.db!!.depenseDao().getCategories()
        val arrayAdapter = CategorieSpinnerAdapter(activity.applicationContext, R.layout.adapter_spinner_categories, cat)
        spi_categorie_depense!!.adapter = arrayAdapter

        save_depense.setOnClickListener{
            var depense = Depense()
            depense._libelleDepense = et_label_depense.text.toString()
            depense._prixDepense = et_montant_depense.text.toString().toDouble()
            depense._idCategorie = (spi_categorie_depense.getItemAtPosition(spi_categorie_depense.selectedItemPosition.toString().toInt()) as Categorie)._idCategorie
            depense._dateDepense = Date()
            BudgetApplication.db!!.depenseDao().saveDepense(depense)
            Toast.makeText(activity.applicationContext, "Dépense enregistrée", Toast.LENGTH_SHORT).show()
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {

            //throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DepenseFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): DepenseFragment {
            val fragment = DepenseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
