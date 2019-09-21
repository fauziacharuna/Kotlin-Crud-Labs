package fauziharuna.dev.kotlincrudlabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import fauziharuna.dev.kotlincrudlabs.adapter.ListProductAdapter
import fauziharuna.dev.kotlincrudlabs.model.Products
import fauziharuna.dev.kotlincrudlabs.model.ProductsData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<Products> = arrayListOf()
    private lateinit var listProductAdapter : ListProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showListProducts()
        setListClickAction()
    }

    private fun showListProducts() {
        list.addAll(ProductsData.listProduct)
        listProductAdapter = ListProductAdapter(list)

        rvProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listProductAdapter
            setHasFixedSize(true)


        }
    }
    private fun setupAddProduct(){
        btnAddProduct.setOnClickListener{
            val detailIntent = Intent(this, DetailsProductActivity::class.java)
            startActivity(detailIntent)
        }
    }
    private fun setListClickAction(){
        listProductAdapter.setOnItemClickCallBack(object  : ListProductAdapter.OnItemClickCallback{
            override fun onItemClick(data: Products) {
                val manageDetailIntent = Intent(this@MainActivity,
                    DetailsProductActivity::class.java).apply {
                    //intent activity to send String
                    putExtra(DetailsProductActivity.EXTRA_NAME, data.name)
                    putExtra(DetailsProductActivity.EXTRA_PRICE, data.price.toString())
                    putExtra(DetailsProductActivity.EXTRA_IMAGE_URL,data.image)
                    Log.i("Data",data.name)
                }
                startActivity(manageDetailIntent)
//                Toast.makeText(this@MainActivity, "Anda memilih ${data.name}",
//                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}
