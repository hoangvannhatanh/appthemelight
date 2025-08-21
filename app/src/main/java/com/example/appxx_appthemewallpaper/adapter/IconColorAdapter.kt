package com.example.appxx_appthemewallpaper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.databinding.ItemColorBinding
import com.example.appxx_appthemewallpaper.extensions.hide
import com.example.appxx_appthemewallpaper.extensions.isSingleCLick
import com.example.appxx_appthemewallpaper.extensions.show
import com.example.appxx_appthemewallpaper.util.CallBack

class IconColorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<String> = arrayListOf()
    private lateinit var context: Context

    private var callBackColor: CallBack.CallBackColor? = null

    var currentPos: Int = 0

    fun callBackColor(callBackColor: CallBack.CallBackColor) {
        this.callBackColor = callBackColor
    }

    fun checkSelectView(pos: Int) {
        val oldPos = currentPos
        currentPos = pos
        notifyItemChanged(pos)
        notifyItemChanged(oldPos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val colorStateList = ColorStateList.valueOf(Color.parseColor(data[position]))
            binding.loItem.backgroundTintList = colorStateList


            if (currentPos == adapterPosition) {
                binding.ivBorder.show()
            } else {
                binding.ivBorder.hide()
            }

            binding.root.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }

                callBackColor?.callBackColor(data[position], position)
            }
        }
    }
}