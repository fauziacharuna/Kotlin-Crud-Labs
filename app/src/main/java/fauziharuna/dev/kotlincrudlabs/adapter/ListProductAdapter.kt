package fauziharuna.dev.kotlincrudlabs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fauziharuna.dev.kotlincrudlabs.R
import fauziharuna.dev.kotlincrudlabs.model.Products
import fauziharuna.dev.kotlincrudlabs.model.ProductsData.listProduct
import kotlinx.android.synthetic.main.item_list_product.view.*

class ListProductAdapter(val listProduct: ArrayList<Products>) :
    RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallback

    fun setOnItemClickCallBack(
        onItemClickCallback: OnItemClickCallback
    ) {
        this.onItemClickCallBack = onItemClickCallback

    }


    interface OnItemClickCallback {
        fun onItemClick(data: Products)

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ListProductAdapter.ListProductViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_product, viewGroup, false)
        return ListProductViewHolder(view)


    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListProductAdapter.ListProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
        holder.itemView.setOnClickListener() {
            onItemClickCallBack.onItemClick(listProduct[holder.adapterPosition])
        }
    }

    class ListProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(products: Products) {
            with(itemView) {
                tvProductName.text = products.name
                tvProductPrice.text = products.price.toString()
                Glide.with(this).load(products.image).into(imgProduct)
            }
        }


    }


}