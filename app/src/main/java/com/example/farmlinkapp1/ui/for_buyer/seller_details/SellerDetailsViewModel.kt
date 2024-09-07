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
    fun getSellerBySaleItemId(saleItemId: ObjectId): User = MongoDB.getSellerBySaleItemId(saleItemId)

    //fun getItemReviews(saleItemId: ObjectId): Flow<List<Review>> = MongoDB.getItemReviews(saleItemId)

    fun raisePhoneCallIntent(context: Context, activity: Activity) {
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
            makePhoneCall(context)
        } else {
            makePhoneCall(context)
        }
    }

    private fun makePhoneCall(context: Context) {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:+916392727418")
        }

        context.startActivity(intent)
    }
}
