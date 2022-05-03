package cu.iptriana.mydog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cu.iptriana.mydog.R
import cu.iptriana.mydog.databinding.ItemDogBinding

class DogAdapter(private var images: List<String> = listOf()) : RecyclerView.Adapter<DogAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDogBinding.bind(view)

        fun bind(image: String) {
            Picasso.get().load(image).into(binding.ivDod)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(images[position])

    override fun getItemCount(): Int = images.size

    fun submitList(newList: List<String>){
        images = newList
        notifyDataSetChanged()
    }
}