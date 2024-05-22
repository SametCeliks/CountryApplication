package com.samet.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.samet.kotlincountries.Adapter.CountryAdapter
import com.samet.kotlincountries.R
import com.samet.kotlincountries.databinding.FragmentFeedBinding
import com.samet.kotlincountries.models.Country
import com.samet.kotlincountries.viewmodel.FeedViewModel
//import kotlinx.android.synthetic.main.fragment_feed.countryError
//import kotlinx.android.synthetic.main.fragment_feed.countryList
//import kotlinx.android.synthetic.main.fragment_feed.countryLoading
//import kotlinx.android.synthetic.main.fragment_feed.swipeRefreshLayout


class FeedFragment (val countryList:ArrayList<Country>)  : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var binding: FragmentFeedBinding
    var swipeRefreshLayout:SwipeRefreshLayout? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)




    }

    private fun findViewById(swipeRefreshLayout: Int): SwipeRefreshLayout? {
        TODO("Not yet implemented")
    }

    private fun setContentView(view: View) {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed,container,false)
        //return dataBinding.root
        return inflater.inflate(R.layout.fragment_feed, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager=LinearLayoutManager(context)
        countryList.adapter=countryAdapter





        /*binding.fragmentButton.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
            */

        swipeRefreshLayout?.setOnRefreshListener{
            countryList.visibility=View.GONE
            countryError.visibility=View.GONE
            binding.countryLoading.visibility=View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()
        }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->

            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {error->
        error?.let {
            if (it){
                countryError.visibility=View.VISIBLE
            }else{
                countryError.visibility=View.GONE
            }
        }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading->
        loading?.let {
            if (it){
                countryLoading.visibility=View.VISIBLE
                countryList.visibility=View.GONE
                countryError.visibility=View.GONE
            }
            else{
                countryLoading.visibility=View.GONE
            }
        }

        })
    }

    }




