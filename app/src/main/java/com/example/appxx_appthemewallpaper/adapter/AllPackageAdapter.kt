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
import com.example.appxx_appthemewallpaper.model.LaunchApp
import com.example.appxx_appthemewallpaper.util.CallBack

class AllPackageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<LaunchApp> = arrayListOf()
    var currentPos: Int = -1

    private var callBackAllPackage: CallBack.CallBackAllPackage? = null

    fun callBackAllPackage(callBackAllPackage: CallBack.CallBackAllPackage) {
        this.callBackAllPackage = callBackAllPackage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLaunchAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(mData: MutableList<LaunchApp>) {
        this.data = mData
    }

    fun updateList(newList: MutableList<LaunchApp>) {
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
            binding.ivLogo.setImageDrawable(data[position].icon)
            binding.txtLanguageTitle.text = data[position].appLabel

            binding.root.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }

                callBackAllPackage?.callBackAllPackage(data[position], position)
            }
        }
    }
}