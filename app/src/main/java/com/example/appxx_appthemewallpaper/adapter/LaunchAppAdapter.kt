package com.example.appxx_appthemewallpaper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.R
import com.example.appxx_appthemewallpaper.databinding.ItemLaunchAppBinding
import com.example.appxx_appthemewallpaper.extensions.isSingleCLick
import com.example.appxx_appthemewallpaper.extensions.setBackGroundDrawable
import com.example.appxx_appthemewallpaper.model.CreateApp
import com.example.appxx_appthemewallpaper.util.CallBack

class LaunchAppAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<CreateApp> = arrayListOf()
    var currentPos: Int = -1

    private var callBackLaunchApp: CallBack.CallBackLaunchApp? = null

    fun callBackLaunchApp(callBackLaunchApp: CallBack.CallBackLaunchApp) {
        this.callBackLaunchApp = callBackLaunchApp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLaunchAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(mData: MutableList<CreateApp>) {
        this.data = mData
    }

    fun updateList(newList: MutableList<CreateApp>) {
        data = newList
        notifyDataSetChanged()
    }

    fun checkSelectView(pos: Int) {
        val oldPos = currentPos
        currentPos = pos
        notifyItemChanged(pos)
        notifyItemChanged(oldPos)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemLaunchAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.ivLogo1.setImageDrawable(data[position].icon1)
            binding.ivLogo2.setImageDrawable(data[position].icon2)
            binding.txtLanguageTitle1.text = data[position].titleName1
            binding.txtLanguageTitle2.text = data[position].titleName2


            if (currentPos == adapterPosition) {
                binding.root.setBackGroundDrawable(R.drawable.background_button_gradient_10)
            } else {
                binding.root.background = null
            }

            binding.root.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }
                callBackLaunchApp?.callBackLaunchApp(data[position], position)
            }
        }
    }
}