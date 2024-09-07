package com.example.farmlinkapp1.ui.for_buyer.seller_details

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.farmlinkapp1.data.MongoDB
import com.example.farmlinkapp1.model.User
import org.mongodb.kbson.ObjectId

class SellerDetailsViewModel : ViewModel() {

    fun getSaleItemById(saleItemId: ObjectId) = MongoDB.getSaleItemById(saleItemId)
    fun getUserByOwnerId(ownerId: String): User = MongoDB.getUserByOwnerId(ownerId)

    //fun getItemReviews(saleItemId: ObjectId): Flow<List<Review>> = MongoDB.getItemReviews(saleItemId)

    fun raisePhoneCallIntent(ownerId: String, context: Context, activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
            makePhoneCall(ownerId, context)
        } else {
            makePhoneCall(ownerId, context)
        }
    }

    private fun makePhoneCall(
        ownerId: String,
        context: Context
    ) {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:+91${MongoDB.getUserPhoneNumber(ownerId)}")
        }

        context.startActivity(intent)
    }
}
