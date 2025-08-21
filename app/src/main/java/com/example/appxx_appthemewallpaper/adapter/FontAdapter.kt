package com.example.appxx_appthemewallpaper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ItemFontBinding
import com.example.appxx_appthemewallpaper.extensions.isSingleCLick
import com.example.appxx_appthemewallpaper.extensions.setBackGroundDrawable
import com.example.appxx_appthemewallpaper.util.CallBack
import com.example.appxx_appthemewallpaper.util.toFraktur
import com.example.appxx_appthemewallpaper.util.toKanit
import com.example.appxx_appthemewallpaper.util.toPoppins
import com.example.appxx_appthemewallpaper.util.toProductSans
import com.example.appxx_appthemewallpaper.util.toRoboto
import com.example.appxx_appthemewallpaper.util.toSatoshi

class FontAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<String> = arrayListOf()
    private lateinit var context: Context

    private var callBackFont: CallBack.CallBackFont? = null

    var currentPos: Int = 0

    fun callBackFont(callBackFont: CallBack.CallBackFont) {
        this.callBackFont = callBackFont
    }

    fun checkSelectView(pos: Int) {
        val oldPos = currentPos
        currentPos = pos
        notifyItemChanged(pos)
        notifyItemChanged(oldPos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFontBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(mData: MutableList<String>) {
        this.data = mData
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(position)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: ItemFontBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            when (data[position]) {
                "Roboto" -> binding.tvFont.text = toRoboto(data[position])
                "Fraktur", "Gothic" -> binding.tvFont.text = toFraktur(data[position])
                "Kanit" -> binding.tvFont.text = toKanit(data[position])
                "Satoshi" -> binding.tvFont.text = toSatoshi(data[position])
                "Poppins" -> binding.tvFont.text = toPoppins(data[position])
                "Product Sans" -> binding.tvFont.text = toProductSans(data[position])
            }

            if (currentPos == adapterPosition) {
                binding.loItem.setBackGroundDrawable(R.drawable.background_button_gradient_30)
            } else {
                binding.loItem.setBackGroundDrawable(R.drawable.background_button_gray_30)
            }

            binding.root.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }

                callBackFont?.callBackFont(data[position], position)
            }
        }
    }
}