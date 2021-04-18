package com.gbulan.shoestore.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gbulan.shoestore.R
import com.gbulan.shoestore.databinding.FragmentShoeDetailBinding
import com.gbulan.shoestore.model.Shoe
import com.gbulan.shoestore.ui.viewmodel.SharedViewModel


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var navController: NavController
    private val viewModel: SharedViewModel by activityViewModels()
    private var shoe = Shoe("","",0.0,"")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        binding.shoe = shoe
        binding.sharedViewModel = viewModel
        binding.executePendingBindings()

        viewModel.eventClick.observe(viewLifecycleOwner, {
            if (it) {
                navController.navigate(action)
                viewModel.navigationCompleted()
            }
        })
    }
}