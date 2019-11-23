package com.good.solscript.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.good.solscript.R
import com.good.solscript.adapter.CategoryAdapter
import com.good.solscript.data.ResponseCategorySubData
import com.good.solscript.data.SampleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_category_content.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryContentFragment : Fragment() {


    private val repository by lazy { SampleRepository() }
    private val categoryList by lazy { mutableListOf<ResponseCategorySubData>() }
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView ", "CategoryContentFragment")

        return inflater.inflate(R.layout.fragment_category_content, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryName = arguments?.getString(CATEGORY_NAME)
        Log.d("categoryName", "" + categoryName)
        categoryRecyclerViewSetup()

        repository.getCategoryDatas(categoryName!!)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.responseCategoryData.responseCategorySubData
            }
            .subscribe({
                it.map {
                    categoryList.add(it)
                    categoryAdapter.data = categoryList

                }
                Log.d("list", "categoryList" + it)
            }, {
                Log.d("categoryList_err", "fail" + it.message)
            })
    }

    companion object {
        private const val CATEGORY_NAME = "category name"
        fun newInstance(categoryName: String): CategoryContentFragment {
            val fragment = CategoryContentFragment()
            val bundle = Bundle()

            bundle.putString(CATEGORY_NAME, categoryName)
            Log.d("category", "" + categoryName)
            fragment.arguments = bundle
            return fragment
        }
    }


    private fun categoryRecyclerViewSetup() {

        recyclerview_categorylist?.run {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }


    }
}
