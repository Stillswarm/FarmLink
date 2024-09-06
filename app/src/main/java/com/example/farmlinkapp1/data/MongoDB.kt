package com.example.farmlinkapp1.data

import android.util.Log
import com.example.farmlinkapp1.model.Buyer
import com.example.farmlinkapp1.model.Category
import com.example.farmlinkapp1.model.Item
import com.example.farmlinkapp1.model.Review
import com.example.farmlinkapp1.model.SaleItem
import com.example.farmlinkapp1.model.Seller
import com.example.farmlinkapp1.model.User
import com.example.farmlinkapp1.util.Constants.APP_ID
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

object MongoDB : MongoDBRepository {
    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureRealm()
        //initializeDB()
    }

    override fun configureRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user = user,
                schema = setOf(
                    Category::class,
                    Item::class,
                    SaleItem::class,
                    User::class,
                    Review::class,
                    Buyer::class,
                    Seller::class,
                )
            )
                .initialSubscriptions { realm ->
                    add(
                        query = realm.query<Category>(),
                        name = "Categories",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<Item>(),
                        name = "Items",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<SaleItem>(),
                        name = "SaleItems",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<Seller>(),
                        name = "Seller",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<Buyer>(),
                        name = "Buyer",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<User>(),
                        name = "Buyer",
                        updateExisting = true
                    )

                    add(
                        query = realm.query<User>(query = "ownerId == $0", user.id),
                        name = "UserData",
                        updateExisting = true
                    )
                }
                //.log(LogLevel.ALL)
                .build()

            try {
                realm = Realm.open(config)
            } catch (e: Exception) {
                Log.d("Realm Exception", e.message!!)
            }

        }

    }

    override fun getAllCategories(): Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }

    override fun getAllItemsByCategoryId(categoryId: ObjectId): Flow<List<Item>> {
        return realm.query<Item>("category._id == $0", categoryId).asFlow().map { it.list }
    }

    override fun getAllSaleItemsByItemId(itemId: ObjectId): Flow<List<SaleItem>> {
        return realm.query<SaleItem>("item._id == $0", itemId).asFlow().map { it.list }
    }

    private fun initializeDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val subscription = realm.subscriptions.update {
                add(realm.query<Category>(), name = "categorySubscription", updateExisting = true)
            }
            subscription.waitForSynchronization()

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
                        "https://i.postimg.cc/Qx6CsLnf/93b5a15f6ea7918d100b238e9995ddca.jpg"
                }

                //fruits
                val item1 = Item().apply {
                    title = "Apple"
                    imageUrl = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    category = category2
                }

                val item2 = Item().apply {
                    title = "Mango"
                    imageUrl = "https://i.postimg.cc/T1Z2q81q/18847dd89b54bd5f4a0bfd77b7440b5d.jpg"
                    category = category2
                }

                val item3 = Item().apply {
                    title = "Pineapple"
                    imageUrl = "https://i.postimg.cc/ydp8x3zG/e190514f1b107b5b1ec278989492d197.jpg"
                    category = category2
                }

                val item4 = Item().apply {
                    title = "Orange"
                    imageUrl = "https://i.postimg.cc/HnJsyWYk/49ffa9b8fb5cddec251e2f5945fa208f.jpg"
                    category = category2
                }

                val seller1 = Seller().apply {
                    ratings = 4
                }

                val seller2 = Seller().apply {
                    ratings = 5
                }

                val seller3 = Seller().apply {
                    ratings = 1
                }

                val user1 = User().apply {
                    name = "Aishwary"
                    picture = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Mumbai, India"
                    phoneNumber = "9555909041"
                    seller = seller1
                }

                val user2 = User().apply {
                    name = "Sudha"
                    picture = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Delhi, India"
                    phoneNumber = "9794233033"
                    seller = seller2
                }

                val user3 = User().apply {
                    name = "Deependra"
                    picture = "https://i.postimg.cc/W3sP7GfJ/farm-fresh-red-apple-156.jpg"
                    address = "Kolkata, India"
                    phoneNumber = "6392727418"
                    seller = seller3
                }

                val saleItem1 = SaleItem().apply {
                    item = item1
                    seller = user1.seller
                    quantityInKg = 10.0
                    pricePerKg = 100.0
                    distance = 10.0
                }

                val saleItem2 = SaleItem().apply {
                    item = item1
                    seller = user2.seller
                    quantityInKg = 150.0
                    pricePerKg = 10.0
                    distance = 5.3
                }

                val saleItem3 = SaleItem().apply {
                    item = item1
                    seller = user1.seller
                    quantityInKg = 40.0
                    pricePerKg = 100.0
                    distance = 1.9
                }

                val saleItem4 = SaleItem().apply {
                    item = item1
                    seller = user3.seller
                    quantityInKg = 100.0
                    pricePerKg = 14.0
                    distance = 2.8
                }

                seller1.user = user1
                seller2.user = user2
                seller3.user = user3
                user1.seller!!.itemsListed.addAll(listOf(saleItem1, saleItem3))
                user2.seller!!.itemsListed.add(saleItem2)
                user3.seller!!.itemsListed.add(saleItem4)
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

                copyToRealm(user1, UpdatePolicy.ALL)
                copyToRealm(user2, UpdatePolicy.ALL)
                copyToRealm(user3, UpdatePolicy.ALL)

                copyToRealm(saleItem1, UpdatePolicy.ALL)
                copyToRealm(saleItem2, UpdatePolicy.ALL)
                copyToRealm(saleItem3, UpdatePolicy.ALL)
                copyToRealm(saleItem4, UpdatePolicy.ALL)
            }
        }
    }

    override fun getItemById(itemId: ObjectId): Item {
        return realm.query<Item>("_id == $0", itemId).first().find()!!
    }

    override fun getCategoryName(categoryId: ObjectId): String {
        return realm.query<Category>("_id == $0", categoryId).find().first().title
    }

    override fun getItemName(itemId: ObjectId): String {
        return realm.query<Item>("_id == $0", itemId).find().first().title
    }

    override fun getItemImageById(itemId: ObjectId): String {
        return realm.query<Item>("_id == $0", itemId).find().first().imageUrl
    }

    override suspend fun addNewSaleItem(
        itemId: ObjectId,
        quantity: Double,
        pricePerKg: Double,
    ) {
        if (user != null) {
            realm.write {
                val item = query<Item>("_id == $0", itemId).find().first()
                val newSaleItem = SaleItem().apply {
                    this.item = findLatest(item)!!
                    quantityInKg = quantity
                    this.pricePerKg = pricePerKg
                    seller = findLatest(getSellerByUserId())
                    ownerId = user.id
                }

                copyToRealm(newSaleItem, UpdatePolicy.ALL)

                val user = query<User>("ownerId == $0", user.id).find().first()
                user.seller!!.itemsListed.add(newSaleItem)
            }
        }
    }

    override fun getSellerByUserId(): Seller {
        return if (user != null) {
            realm.query<User>("ownerId == $0", user.id).find().first().seller!!
        } else Seller()
    }

    override fun userKnown(): Boolean {
        if (user != null) {
            val existingUser = realm.query<User>("ownerId == $0", user.id).find()
            return !existingUser.isEmpty()
        } else return false
    }

    override suspend fun createUser(address: String, phoneNo: String) {
        if (user != null) {
            val customUserData = user.profileAsBsonDocument()
            realm.write {
                if (!userKnown()) {
                    val user = User().apply {
                        ownerId = user.id
                        this.address = address
                        phoneNumber = phoneNo
                        name = customUserData.getString("name").value
                        picture = customUserData.getString("picture").value
                        email = customUserData.getString("email").value
                    }
                    copyToRealm(user, UpdatePolicy.ALL)
                }
            }
        }
    }

    override suspend fun addSellerToUser() {
        if (user != null) {
            realm.write {
                val user = query<User>("ownerId == $0", user.id).find().first()
                if (user.seller == null) {
                    user.seller = Seller().apply {
                        this.user = user
                    }
                }
                copyToRealm(user, UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun addBuyerToUser() {
        if (user != null) {
            realm.write {
                val user = query<User>("ownerId == $0", user.id).find().first()
                if (user.buyer == null) {
                    user.buyer = Buyer().apply {
                        this.user = user
                    }
                }

                copyToRealm(user, UpdatePolicy.ALL)
            }
        }
    }

    override fun getAllSaleItemsForSeller(): Flow<List<SaleItem>> {
//        return if (user != null) {
//            realm.query<SaleItem>("ownerId == $0", user.id).asFlow().map { it.list }
//        } else emptyList<List<SaleItem>>().asFlow()

        if (user != null) {
            val x = realm.query<SaleItem>("ownerId == $0", user.id).asFlow().map { it.list }
            Log.d("fuck", user.id)
            return x
        } else {
            Log.d("fuck", "empty")
            return emptyList<List<SaleItem>>().asFlow()
        }
    }

    override fun getUser(): User {
        return if (user == null) User()
        else realm.query<User>("ownerId == $0", user.id).find().first()
    }

    override fun getSellerNameFromSaleItemId(saleItemId: ObjectId) : String {
        return realm.query<User>("seller.user._id == $0", saleItemId).find().first().seller?.user?.name ?: "Item"
    }
}