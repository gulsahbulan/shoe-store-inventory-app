package com.gbulan.shoestore.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gbulan.shoestore.R
import com.gbulan.shoestore.databinding.FragmentShoeListBinding
import com.gbulan.shoestore.databinding.ListItemBinding
import com.gbulan.shoestore.ui.viewmodel.SharedViewModel


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var navController: NavController
    private val viewmodel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        binding.sharedViewModel = viewmodel
        viewmodel.shoeData.observe(viewLifecycleOwner, {
            for (shoe in it) {
                val listItemBinding: ListItemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.list_item, binding.shoeList, false)
                listItemBinding.shoe = shoe
                binding.shoeList.addView(listItemBinding.root)
            }
        })

        viewmodel.eventClick.observe(viewLifecycleOwner, {
            if (it) {
                navController.navigate(action)
                viewmodel.navigationCompleted()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_shoelist, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            navController.navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }
}