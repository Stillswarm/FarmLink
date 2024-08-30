package com.example.farmlinkapp.navigation
//
//import android.net.Uri
//import android.os.Bundle
//import androidx.navigation.NavType
//import com.example.farmlinkapp.model.Category
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//
//object CustomNavType {
//
//    val categoryType = object : NavType<Category>(isNullableAllowed = false) {
//        override fun put(bundle: Bundle, key: String, value: Category) {
//            return bundle.putString(key, Json.encodeToString(value))
//        }
//
//        override fun serializeAsValue(value: Category): String {
//            return Uri.encode(Json.encodeToString(value))
//        }
//
//        override fun get(bundle: Bundle, key: String): Category? {
//            return Json.decodeFromString(bundle.getString(key) ?: return null)
//        }
//
//        override fun parseValue(value: String): Category {
//            return Json.decodeFromString(Uri.decode(value))
//        }
//    }
//}
