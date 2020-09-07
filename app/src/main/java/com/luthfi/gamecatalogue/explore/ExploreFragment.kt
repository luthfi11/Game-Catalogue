package com.luthfi.gamecatalogue.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.luthfi.gamecatalogue.R
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private val exploreViewModel: ExploreViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }
}