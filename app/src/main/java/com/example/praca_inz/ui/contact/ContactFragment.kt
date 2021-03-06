package com.example.praca_inz.ui.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.praca_inz.R
import com.example.praca_inz.databinding.FragmentContactBinding
import com.example.praca_inz.network.ContactApiFilter


class ContactFragment : Fragment() {

    private val contactViewModel: ContactViewModel by lazy {
        ViewModelProvider(this)[ContactViewModel::class.java]
    }


    private lateinit var binding: FragmentContactBinding


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.contactViewModel = contactViewModel


        contactViewModel.goToAddContact.observe(viewLifecycleOwner, { goOpen ->
            if(goOpen){
                openAddContact()
                contactViewModel.addContactFinish()
            }
        })


        binding.contactGrid.adapter = ContactGridAdapter(ContactGridAdapter.OnClickListener {
            contactViewModel.displayPropertyDetails(it)
        })

        contactViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(ContactFragmentDirections.actionNavigationContactToDetailContactFragment(it))
                contactViewModel.displayPropertyDetailsComplete()
            }
        })

        binding.animalButton.setOnClickListener {
            contactViewModel.updateFilter(
                    ContactApiFilter.SHOW_ANIMAL,
            )
        }
        binding.chemistryButton.setOnClickListener {
            contactViewModel.updateFilter(
                ContactApiFilter.SHOW_CHEMISTRY,
            )
        }
        binding.plantButton.setOnClickListener {
            contactViewModel.updateFilter(
                ContactApiFilter.SHOW_PLANT,
            )
        }
        return binding.root
    }
    private fun openAddContact(){
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_navigation_contact_to_addContactFragment)

    }




}