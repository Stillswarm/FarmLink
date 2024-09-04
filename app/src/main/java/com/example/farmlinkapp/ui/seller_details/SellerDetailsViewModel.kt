package com.example.farmlinkapp.ui.seller_details

import androidx.lifecycle.ViewModel
import com.example.farmlinkapp.model.Review
import com.example.farmlinkapp.model.Seller
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SellerDetailsViewModel : ViewModel() {
    private val _seller = MutableStateFlow(Seller())
    val seller: StateFlow<Seller> = _seller.asStateFlow()

    init {
        fetchSellerDetails()
    }

    private fun fetchSellerDetails() {
        // Mock data to simulate fetching seller details
        val sampleSeller = Seller().apply {
            name = "Brijesh Lal"
            profilePicUrl = "https://example.com/profile-pic-url"
            address = "Brijesh Lal, Dipatoli Mandi, Khelgaon, Ranchi (834010)"
            phoneNumber = "1234567890"
            rating = 4.0
            reviewsCount = 12
            quantity = 57
            distance = 2.1
            pricePerKg = 15
            reviews.apply {
                add(Review().apply {
                    reviewerName = "Sunny Bhaiya"
                    rating = 0
                    reviewText = "Bhot kharab hai inki (sabjiya)..."
                })
                add(Review().apply {
                    reviewerName = "Surya Bhaiya"
                    rating = 7
                    reviewText = "Bhot hi sungandhit hai inki (sabjiya)..."
                })
            }
        }

        _seller.value = sampleSeller
    }
}
