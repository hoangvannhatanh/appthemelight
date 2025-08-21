package com.example.appxx_appthemewallpaper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.appxx_appthemewallpaper.databinding.ItemCreateAppBinding
import com.example.appxx_appthemewallpaper.extensions.isSingleCLick
import com.example.appxx_appthemewallpaper.model.CreateApp
import com.example.appxx_appthemewallpaper.util.*

class CreateEditShortcutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<CreateApp> = arrayListOf()
    private lateinit var context: Context
    private var strFont = ""

    private var callBackLaunchApp: CallBack.CallBackLaunchApp? = null

    fun callBackLaunchApp(callBackLaunchApp: CallBack.CallBackLaunchApp) {
        this.callBackLaunchApp = callBackLaunchApp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCreateAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
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

    fun updateFont(strFont: String) {
        this.strFont = strFont
        notifyDataSetChanged()
    }

    fun updateTintColor(strBackground: String, strColor: String) {
        this.strFont = strFont
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(position)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: ItemCreateAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.txtLanguageTitle1.isSelected = true
            binding.txtLanguageTitle2.isSelected = true

            binding.ivLogo1.setImageDrawable(data[position].icon1)
            binding.ivLogo2.setImageDrawable(buildIcon(data[position].backgroundCreate, data[position].iconCreate))
            binding.txtLanguageTitle1.text = data[position].titleName1

            when (strFont) {
                "Default" -> binding.txtLanguageTitle2.text = data[position].titleName2
                "Roboto" -> binding.txtLanguageTitle2.text = toRoboto(data[position].titleName2)
                "General Sans" -> binding.txtLanguageTitle2.text = toGeneralsans(data[position].titleName2)
                "Helvetica Neue" -> binding.txtLanguageTitle2.text = toHelveticaNeue(data[position].titleName2)
                "Fraktur", "Gothic" -> binding.txtLanguageTitle2.text = toFraktur(data[position].titleName2)
                "Kanit" -> binding.txtLanguageTitle2.text = toKanit(data[position].titleName2)
                "Satoshi" -> binding.txtLanguageTitle2.text = toSatoshi(data[position].titleName2)
                "Poppins" -> binding.txtLanguageTitle2.text = toPoppins(data[position].titleName2)
                "Product Sans" -> binding.txtLanguageTitle2.text = toProductSans(data[position].titleName2)
            }

            binding.cvLogo1.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }
                if (data[position].packageName1.isEmpty()) {
                    callBackLaunchApp?.callBackImportApp(position)
                }
            }

            binding.tvCreate.setOnClickListener {
                if (!isSingleCLick()) {
                    return@setOnClickListener
                }

                if (data[position].packageName1.isNotEmpty()) {
                    callBackLaunchApp?.callBackCreateShortcut(data[position], position, binding.txtLanguageTitle2.text.toString())
                }
            }
        }
    }

    private fun buildIcon(backGround: Int, icon: Int): Drawable {
        val size = context.resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val bg = AppCompatResources.getDrawable(context, backGround)!!
        val fg = AppCompatResources.getDrawable(context, icon)!!

        bg.setBounds(0, 0, size, size)
        bg.draw(canvas)

        fg.setBounds(0, 0, size, size)
        fg.draw(canvas)

        // Chuyển bitmap thành drawable
        return BitmapDrawable(context.resources, bitmap)
    }
}