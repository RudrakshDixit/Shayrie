package com.example.shairye


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import android.R.attr.label
import android.content.ClipData
import android.support.v4.content.ContextCompat.getSystemService
import android.R.attr.label
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.Toast
import android.support.v4.content.ContextCompat.getSystemService




class Adapter(val context: Context, val shairye: List<Shairye> ) : RecyclerView.Adapter<Adapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): MyViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shairye.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val f= shairye[position]
        holder.setData(f,position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var currentshairye:Shairye?= null
        var currentpos: Int = 0


        init {

            itemView.copy.setOnClickListener {
                val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(null, currentshairye!!.title)
                clipboard.primaryClip = clip
                Toast.makeText(context, "Text Copied", Toast.LENGTH_SHORT).show();

            }

            itemView.share.setOnClickListener {
                val intent = Intent()
                intent.action= Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,currentshairye!!.title)
                intent.type="text/plain"

                context.startActivity(Intent.createChooser(intent,"Please select app: "))
            }
        }

        fun setData(f: Shairye?,pos:Int){
           itemView.textView.text= f!!.title

            this.currentshairye = f
            this.currentpos =pos
        }
    }
}