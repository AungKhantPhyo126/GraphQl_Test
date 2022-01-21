package com.rts.spacex_launch.launchDetail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.rts.spacex_launch.R
import com.rts.spacex_launch.databinding.FragmentLaunchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchDetailFragment : Fragment() {
    private lateinit var binding:FragmentLaunchDetailBinding
    private val viewModel by viewModels<LaunchDetailViewModel>()
    private val args by navArgs<LaunchDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLaunchDetailBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLaunchDetail(args.launchId)
        viewModel.launchDetails.observe(viewLifecycleOwner){
            binding.launchdetail=it
        }

        binding.tvWikiLink.setOnClickListener {
            val uri=Uri.parse(binding.tvWikiLink.text.toString())
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }
    }
}