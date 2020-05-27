package com.harlie.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.harlie.dogs.R
import com.harlie.dogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dogs.view.*

class DogsListAdapter(val dogsList: ArrayList<DogBreed>): RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {
    private val TAG = "LEE: <" + DogsListAdapter::class.java.simpleName + ">"

    fun updateDogList(newDogList: List<DogBreed>) {
        Timber.tag(TAG).d("updateDogList")
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        Timber.tag(TAG).d("onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_dogs, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        Timber.tag(TAG).d("onBindViewHolder")
        holder.view.dogName.text = dogsList[position].breedName
        holder.view.dogLifespan.text = dogsList[position].breedLifespan
        holder.view.setOnClickListener {view ->
            Timber.tag(TAG).d("-CLICK- $holder")
            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            action.dogUuid = dogsList[position].breedId!!.toInt()
            view.findNavController().navigate(action)
        }
    }

    class DogViewHolder(var view: View): RecyclerView.ViewHolder(view)

}