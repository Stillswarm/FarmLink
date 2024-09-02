package com.example.farmlinkapp1.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmlinkapp1.model.Item
import com.example.farmlinkapp1.model.Category
import com.example.farmlinkapp1.model.SaleItems
import com.example.farmlinkapp1.model.Seller
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realm: Realm
) : ViewModel() {

    init {
        initializeDB()
    }

    val itemCategories = realm.query<Category>()
        .asFlow()
        .map { it.list }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private fun initializeDB() {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write {
                val category1 = Category().apply {
                    title = "Vegetables"
                    imageUrl = "https://i.postimg.cc/cCfgtDRV/vegetables.webp"
                }

                val category2 = Category().apply {
                    title = "Fruits"
                    imageUrl = "https://i.postimg.cc/xT0TPTnk/368872.jpg"
                }

                val category3 = Category().apply {
                    title = "Cereals"
                    imageUrl =
                        "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                }

                val category4 = Category().apply {
                    title = "Pulses"
                    imageUrl =
                        "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                }

                val item1 = Item().apply {
                    title = "Apple"
                    imageUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    category = category2
                }

                val item2 = Item().apply {
                    title = "Mango"
                    imageUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    category = category2
                }

                val item3 = Item().apply {
                    title = "Pineapple"
                    imageUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    category = category2
                }

                val item4 = Item().apply {
                    title = "Orange"
                    imageUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    category = category2
                }

                val seller1 = Seller().apply {
                    name = "Seller1"
                    profilePicUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Mumbai, India"
                    phoneNumber = "9555909041"
                }

                val seller2 = Seller().apply {
                    name = "Seller2"
                    profilePicUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Delhi, India"
                    phoneNumber = "9794233033"
                }

                val seller3 = Seller().apply {
                    name = "Seller3"
                    profilePicUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Kolkata, India"
                    phoneNumber = "6392727418"
                }

                val saleItem1 = SaleItems().apply {
                    item = item1
                    seller = seller1
                    quantityInKg = 10.0
                    pricePerKg = 100.0
                    distance = 10.0
                }

                val saleItem2 = SaleItems().apply {
                    item = item1
                    seller = seller2
                    quantityInKg = 150.0
                    pricePerKg = 10.0
                    distance = 5.3
                }

                val saleItem3 = SaleItems().apply {
                    item = item1
                    seller = seller1
                    quantityInKg = 40.0
                    pricePerKg = 100.0
                    distance = 1.9
                }

                val saleItem4 = SaleItems().apply {
                    item = item1
                    seller = seller3
                    quantityInKg = 100.0
                    pricePerKg = 14.0
                    distance = 2.8
                }

                seller1.saleItems.addAll(listOf(saleItem1, saleItem3))
                seller2.saleItems.add(saleItem2)
                seller3.saleItems.add(saleItem4)
                category2.items.addAll(listOf(item1, item2, item3, item4))
                item1.saleItems.addAll(listOf(saleItem1, saleItem2, saleItem3, saleItem4))

                copyToRealm(category1, UpdatePolicy.ALL)
                copyToRealm(category2, UpdatePolicy.ALL)
                copyToRealm(category3, UpdatePolicy.ALL)
                copyToRealm(category4, UpdatePolicy.ALL)

                copyToRealm(item1, UpdatePolicy.ALL)
                copyToRealm(item2, UpdatePolicy.ALL)
                copyToRealm(item3, UpdatePolicy.ALL)
                copyToRealm(item4, UpdatePolicy.ALL)
            }
        }
    }
}