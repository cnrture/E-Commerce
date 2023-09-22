package com.canerture.ecommerce.common

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.canerture.ecommerce.databinding.DialogPopUpBinding

/**
 * Created on 10.08.2023
 * @author Caner Türe
 */

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun setViewsVisible(vararg views: View) = views.forEach {
    it.visible()
}


fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.setStrikeThrough() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun Fragment.showPopup(
    errorMsg: String? = null,
    okayButtonListener: (() -> Unit)? = null,
) {
    val dialog = Dialog(requireContext())
    val binding = DialogPopUpBinding.inflate(dialog.layoutInflater, null, false)
    dialog.setContentView(binding.root)
    dialog.setWidthPercent(80)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(false)

    binding.tvError.text = errorMsg

    binding.btnOkay.setOnClickListener {
        if (!requireActivity().isFinishing) {
            dialog.dismiss()
        }
        okayButtonListener?.invoke()
    }

    if (!requireActivity().isFinishing) {
        dialog.show()
    }
}

fun Dialog.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}