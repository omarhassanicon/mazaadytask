package com.jony.mazaadytask.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jony.mazaadytask.R
import com.jony.mazaadytask.data.model.response.GetAllCatsResponse
import com.jony.mazaadytask.data.model.response.GetOptionsChildResponse
import com.jony.mazaadytask.data.model.response.PropertiesCatResponse
import com.jony.mazaadytask.databinding.FragmentHomeBinding
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.presentation.basefragment.BaseFragment
import com.jony.mazaadytask.presentation.home.adapter.PropertiesAdapter
import com.jony.mazaadytask.presentation.home.viewmodel.GetAllViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: GetAllViewModel
    private lateinit var categoryAdapter: ArrayAdapter<String>

    @Inject
    lateinit var adapter: PropertiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }


    private fun initialization() {
        initViewModel()
        initRV()
        initListeners()
    }

    private fun initListeners() {
        binding!!.secondScreenBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSecondScreenFragment()
            findNavController().navigate(action)
        }
        binding?.main?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val selectedCategory = s?.toString()
                val categoryId = getCategoryById(selectedCategory)?.id
                categoryId?.let { viewModel.getPropertiesCatsData(it) }

            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
        binding?.subcategory?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val selectedSubcategory = s?.toString()
                val optionId = getOptionIdBySubcategory(selectedSubcategory)
                optionId?.let { viewModel.getOptionData(it) }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


    private fun getOptionIdBySubcategory(subcategoryName: String?): Int? {
        return viewModel.getOptionLiveData().value?.data?.data?.find { it?.name == subcategoryName }?.id
    }

    private fun getCategoryById(categoryName: String?): GetAllCatsResponse.Data.Category? {
        return viewModel.getAllCatsLiveData().value?.data?.data?.categories?.find { it?.name == categoryName }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(GetAllViewModel::class.java)
        viewModel.getAllCatsLiveData().observe(viewLifecycleOwner, getAllCatsObserver)
        viewModel.getOptionLiveData().observe(viewLifecycleOwner, propertiesObserver)
    }

    private fun initRV() {
        binding?.propertiesRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        binding?.propertiesRecyclerview?.adapter = adapter
    }

    private var getAllCatsObserver: Observer<Resource<GetAllCatsResponse?>> = Observer {
        when (it.status) {
            Resource.Status.LOADING -> {
            }
            Resource.Status.SUCCESS -> {
                val categoryNames = mutableListOf<String>()
                val childrenNames = mutableListOf<String>()
                it.data?.data?.categories?.forEach { category ->
                    category?.name?.let { categoryName ->
                        categoryNames.add(categoryName)
                        category.children?.forEach { child ->
                            child?.name?.let { childName ->
                                childrenNames.add(childName)
                            }
                        }
                    }
                }
                categoryAdapter =
                    ArrayAdapter(requireContext(), R.layout.dropdown_item, categoryNames)
                binding?.main?.setAdapter(categoryAdapter)
                val childrenAdapter =
                    ArrayAdapter(requireContext(), R.layout.dropdown_item, childrenNames)
                binding?.subcategory?.setAdapter(childrenAdapter)

            }
            Resource.Status.API_ERROR -> {
                handleError(it.error_msg.toString())
            }
            Resource.Status.DOMAIN_ERROR -> {
                handleError(it.throwable.toString())
            }
        }
    }
    private var propertiesObserver: Observer<Resource<GetOptionsChildResponse?>> = Observer {
        when (it.status) {
            Resource.Status.LOADING -> {
            }
            Resource.Status.SUCCESS -> {
                adapter.setData(it.data?.data as List<GetOptionsChildResponse.Data.Option>)
            }
            Resource.Status.API_ERROR -> {
                handleError(it.error_msg.toString())
            }
            Resource.Status.DOMAIN_ERROR -> {
                handleError(it.throwable.toString())
            }
        }
    }

}