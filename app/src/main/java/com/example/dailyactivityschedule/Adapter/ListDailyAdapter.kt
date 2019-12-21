package com.example.dailyactivityschedule.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dailyactivityschedule.Model.DailyModel
import com.example.dailyactivityschedule.R
import com.example.dailyactivityschedule.ViewModel.DailyViewModel
import com.example.dailyactivityschedule.databinding.ListDailyBinding

class ListDailyAdapter(private val viewModel: DailyViewModel) : ListAdapter<DailyModel, ListDailyAdapter.MyViewHolder>(
    ItemDiffCallback()
) {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListDailyBinding.inflate(inflater,parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtDay.text = getItem(holder.adapterPosition).namahari
        holder.txtActivity.text = getItem(holder.adapterPosition).activitas

            //delete
        holder.imgDelete.setOnClickListener {
            viewModel.removeItem(getItem(holder.adapterPosition))
        }

        //edit
        holder.imgEdited.setOnClickListener {
            val context = holder.itemView.context

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.activity_edited, null)

            //mengambil data sebelumnya
            val prevHari = getItem(holder.adapterPosition).namahari
            val prevActivity = getItem(holder.adapterPosition).activitas
            val editNamaHari = view.findViewById<TextView>(R.id.editDailyTitleEdited)
            val editActivitas = view.findViewById<TextView>(R.id.editDailyContentEdited)
            editNamaHari.text = prevHari
            editActivitas.text = prevActivity

            //dialog
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Daily")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener {
                    dialog, id ->

                    //edit
                    val editedNamaHari = editNamaHari.text.toString()
                    val editedActivity = editActivitas.text.toString()
                    getItem(holder.adapterPosition).namahari = editedNamaHari
                    getItem(holder.adapterPosition).activitas = editedActivity
                   viewModel.updateItem(getItem(holder.adapterPosition))
                    holder.txtDay.text = editedNamaHari
                    holder.txtActivity.text = editedActivity
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialog, id -> })

            //tampil dialog
            alertDialog.create().show()
        }
    }

    class MyViewHolder(val binding: ListDailyBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtDay = binding.textDay
        val txtActivity = binding.txtActivity
        val imgEdited = binding.imageEdit
        val imgDelete = binding.imageDelete

    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<DailyModel>(){
    override fun areItemsTheSame(p0: DailyModel, p1: DailyModel): Boolean {
        return p0.Id == p1.Id
    }

    override fun areContentsTheSame(p0: DailyModel, p1: DailyModel): Boolean {
        return p0.equals(p1)
    }

}