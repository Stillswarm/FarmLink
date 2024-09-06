package com.example.farmlinkapp1.ui.for_buyer.seller_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.farmlinkapp1.model.Item
import com.example.farmlinkapp1.model.Review
import com.example.farmlinkapp1.model.SaleItem
import com.example.farmlinkapp1.model.Seller
import com.example.farmlinkapp1.model.User

class SellerDetailsViewModel : ViewModel() {
    private val _seller = MutableStateFlow(Seller())
    val seller: StateFlow<Seller> = _seller.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _item = MutableStateFlow(Item())
    val item: StateFlow<Item> = _item.asStateFlow()

    private val _saleItem = MutableStateFlow(SaleItem())
    val saleItem: StateFlow<SaleItem> = _saleItem.asStateFlow()

    private val _review = MutableStateFlow(Review())
    val review: StateFlow<Review> = _review.asStateFlow()
//    init {
//        fetchSellerDetails()
//    }

//    private fun fetchSellerDetails() {
//        // Mock data to simulate fetching seller details
//        val sampleSeller = Seller().apply {
//            ratings = 4
//            reviewsCount = 12
//            quantity = 57
//            distance = 2.1
//            pricePerKg = 15
//            reviews.apply {
//                add(Review().apply {
//                    reviewerName = "Sunny Bhaiya"
//                    rating = 0
//                    reviewText = "Bhot kharab hai inki (sabjiya)..."
//                })
//                add(Review().apply {
//                    reviewerName = "Surya Bhaiya"
//                    rating = 7
//                    reviewText = "Bhot hi sungandhit hai inki (sabjiya)..."
//                })
//            }
//        }
//        val sampleUser = User().apply(){
//            name = "Brijesh Lal"
//            profilePicUrl = "https://example.com/profile-pic-url"
//            address = "Brijesh Lal, Dipatoli Mandi, Khelgaon, Ranchi (834010)"
//            phoneNumber = "1234567890"
//        }
//
//        _seller.value = sampleSeller
//    }

}
