package com.a13hay.clientapplication.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.R
import com.a13hay.clientapplication.model.Model
import com.a13hay.clientapplication.view.ViewHolder
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList


class MyAdapter(context:Context, list: ArrayList<Model>,activity: Activity) : RecyclerView.Adapter<ViewHolder>() {
    var mContext = context
    var mlist = list
    var mActivity = activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_list_image,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(mContext).load(mlist[position].imageUrl).into(holder.mImage)
        holder.mDownload.setOnClickListener {
            holder.mProgrssBar.visibility= View.VISIBLE
            saveImage(holder)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveImage(holder: ViewHolder) {
        ActivityCompat.requestPermissions(mActivity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        var fileOutputStream: FileOutputStream? = null
        var file:File = getDisc()
        if(!file.exists() && !file.mkdirs()){
            file.mkdirs()
        }
        var simpleDataFormat: SimpleDateFormat = SimpleDateFormat("yyyymmhhmmss")
        var date = simpleDataFormat.format(Date())
        var name = "IMG$date.jpg"
        var file_name:String = file.absoluteFile.toString() +  "/" + name
        var new_file:File = File(file_name).absoluteFile

        try {
            holder.mProgrssBar.visibility = View.GONE
            var draw: BitmapDrawable = holder.mImage.drawable as BitmapDrawable
            var bitmap:Bitmap = draw.bitmap
            fileOutputStream = FileOutputStream(new_file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream)
            Toast.makeText(mContext,"Image downloaded successfully",Toast.LENGTH_SHORT).show()
            fileOutputStream.flush()
            fileOutputStream.close()

        }
        catch (e:FileNotFoundException){
            e.printStackTrace()
        }
        catch (e:IOException){
            e.printStackTrace()
        }
        refreahGallery(new_file)
    }

    private fun refreahGallery(newFile: File) {
        var intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(newFile)
        mContext.sendBroadcast(intent)
    }

    private fun getDisc(): File {
        var file:File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File(file.toString())
    }

    override fun getItemCount(): Int {
       return mlist.size
    }
}