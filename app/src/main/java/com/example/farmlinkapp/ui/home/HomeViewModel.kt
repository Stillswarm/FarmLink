package com.example.farmlinkapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmlinkapp.model.Item
import com.example.farmlinkapp.model.Category
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
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                }

                val category4 = Category().apply {
                    title = "Pulses"
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"

                }

                val item1 = Item().apply {
                    title = "Cabbage"
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                    category = category1
                }

                val item2 = Item().apply {
                    title = "Tomato"
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                    category = category1
                }

                val item3 = Item().apply {
                    title = "Potato"
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                    category = category1
                }

                val item4 = Item().apply {
                    title = "Onion"
                    imageUrl = "https://i.postimg.cc/MGjdrwqR/stock-photo-cereal-grains-seeds-beans-379660966.jpg"
                    category = category1
                }

                category1.items.addAll(listOf(item1, item2, item3, item4))

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