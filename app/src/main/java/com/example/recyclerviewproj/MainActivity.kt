package com.example.recyclerviewproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproj.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val people = arrayListOf<Person>()
        for (i in 0..30) {
            people.add(Person("홍길동 $i", 10))
        }

        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PersonAdapter(people) {
                Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class PersonAdapter(val items: List<Person>, private val clickListener:(person: Person)->Unit) : RecyclerView.Adapter<PersonAdapter.PersonViewModel>() {
    class PersonViewModel(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        val viewHolder = PersonViewModel(ItemPersonBinding.bind(view))
        view.setOnClickListener{
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PersonViewModel, position: Int) {
        holder.binding.person = items[position]
    }
}
