package com.example.shoestoreinventoryapp

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestoreinventoryapp.databinding.FragmentShoesListBinding


class ShoesListFragment : Fragment() {

    private val viewModel: ShoesListViewModel by activityViewModels()


    private lateinit var binding: FragmentShoesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes_list,
            container,
            false
        )
        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoesList ->
            shoesList.forEach {
                addView(requireContext(),it)

            }
        })
        binding.goToDetailsButton.setOnClickListener {
            val action = ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment()
            it.findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.log_out_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = ShoesListFragmentDirections.actionShoesListFragmentToLogInFragment()
        requireView().findNavController().navigate(action)
      return super.onOptionsItemSelected(item)
    }

    // to remove the bar back button
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun addView( context: Context, shoeObject: ShoeObject){
        val shoeNameTextView = TextView(context)
        shoeNameTextView.gravity = Gravity.CENTER_HORIZONTAL

        val shoeCompanyTextView = TextView(context)
        shoeCompanyTextView.gravity = Gravity.CENTER_HORIZONTAL

        val shoeSizeTextView = TextView(context)
        shoeSizeTextView.gravity = Gravity.CENTER_HORIZONTAL
        shoeSizeTextView.setTextColor(resources.getColor(R.color.colorRed))

        val shoeDescriptionTextView = TextView(context)
        shoeDescriptionTextView.gravity = Gravity.CENTER_HORIZONTAL

        shoeNameTextView.text = shoeObject.shoeName
        shoeCompanyTextView.text = shoeObject.shoeCompany
        shoeSizeTextView.text = shoeObject.shoeSize
        shoeDescriptionTextView.text = getString(R.string.shoe_description_text_view,shoeObject.shoeDescription)

        binding.linearLayout.addView(shoeNameTextView)
        binding.linearLayout.addView(shoeCompanyTextView)
        binding.linearLayout.addView(shoeSizeTextView)
        binding.linearLayout.addView(shoeDescriptionTextView)


    }

}