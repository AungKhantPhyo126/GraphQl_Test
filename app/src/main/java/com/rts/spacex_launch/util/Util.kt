package com.rts.spacex_launch.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rts.spacex_launch.R

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl.let {
        Glide.with(imageView.context).load(it)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}
fun showDialog(context: Context, message: String, view: View) {
    MaterialAlertDialogBuilder(context)
        .setMessage(message)
        .setNeutralButton(context.resources.getString(R.string.cancel)) { dialog, _ ->
            // Respond to neutral button press
            dialog.dismiss()
            view.findNavController().popBackStack()
        }
        .show()
}