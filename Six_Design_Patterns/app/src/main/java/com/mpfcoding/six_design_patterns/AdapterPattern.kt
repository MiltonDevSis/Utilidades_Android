package com.mpfcoding.six_design_patterns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterPattern(
    private val hamburguer: List<HamburguerBuilderPattern>
) : RecyclerView.Adapter<AdapterPattern.HamburguerViewHolder>() {

    inner class HamburguerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamburguerViewHolder {
        return HamburguerViewHolder(LayoutInflater.from(parent.context).inflate(0, parent, false))
    }

    override fun onBindViewHolder(holder: HamburguerViewHolder, position: Int) {}

    override fun getItemCount(): Int{
        return hamburguer.size
    }
}