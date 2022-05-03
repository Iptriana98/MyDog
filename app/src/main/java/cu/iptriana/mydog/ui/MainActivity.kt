package cu.iptriana.mydog.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import cu.iptriana.mydog.data.api.APIService
import cu.iptriana.mydog.databinding.ActivityMainBinding
import cu.iptriana.mydog.ui.adapters.DogAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
//    private val viewModel: MainViewModel by viewmodels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) searchByName(p0.lowercase())
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun initRecycler() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DogAdapter()
        }
    }

    private fun getRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://dog.ceo/api/bread/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun searchByName(query: String) = CoroutineScope(Dispatchers.IO).launch {
        val call = getRetrofit().create(APIService::class.java).getDogsByBreads("$query/images")
        val puppies = call.body()
        runOnUiThread {
            if (call.isSuccessful) {
                puppies?.let { (binding.recyclerView.adapter as DogAdapter).submitList(it.images) }
            } else {
                Toast.makeText(this@MainActivity, "Error in loading", Toast.LENGTH_SHORT).show()
            }
        }

    }
}