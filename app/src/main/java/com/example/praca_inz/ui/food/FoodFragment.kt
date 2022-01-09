package com.example.praca_inz.ui.food

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.praca_inz.databinding.FragmentFoodBinding
import com.example.praca_inz.ui.food.addFood.AddFoodFragment


class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private val foodViewModel: FoodViewModel by lazy {
        ViewModelProvider(this)[FoodViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.foodViewModel = foodViewModel

        //OPEN ADD MEAL
        foodViewModel.eventOpenPopupMenu.observe(viewLifecycleOwner, { goOpen ->
            if(goOpen){
                openAddContact()
                foodViewModel.openPopupMenuFinished()
            }
        })

        //OPEN LIST FROM DATABASE
        binding.foodGrid.adapter = FoodGridAdapter()
        return binding.root
    }

    fun openAddContact(){
        val dialog = AddFoodFragment()
        dialog.show(requireActivity().supportFragmentManager, "ADD CONTACT THINGS")

    }
}