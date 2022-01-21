package com.rts.graphqltest.launchList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.rts.graphqltest.ViewState
import com.rts.graphqltest.databinding.FragmentLaunchListBinding
import com.rts.graphqltest.util.showDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LaunchListFragment : Fragment() {
    private lateinit var binding:FragmentLaunchListBinding
    private val viewModel by viewModels<LaunchListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLaunchListBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MyLaunchItemRecyclerViewAdapter()
        binding.rvLaunchList.adapter=adapter

        viewModel.viewStateLive.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading->{
                    binding.gpContainer.isVisible = false
                    binding.lavLoadState.isVisible = true
                    binding.lavLoadState.playAnimation()
                }
                is ViewState.Success->{
                    binding.gpContainer.isVisible = true
                    binding.lavLoadState.isVisible = false
                    binding.lavLoadState.cancelAnimation()
                    adapter.submitList(it.data)
                    binding.tvNoResult.isVisible = it.data.isNullOrEmpty()
                }
                is ViewState.Error->{
                    binding.gpContainer.isVisible = false
                    binding.lavLoadState.isVisible = false
                    binding.lavLoadState.cancelAnimation()
                    showDialog(requireContext(), it.error.message.orEmpty(), view)
                }
            }
        }
    }
}