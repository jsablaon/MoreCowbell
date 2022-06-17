package com.isit322.back4appmyfavcoffee

import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

public class DbHelper() {

    fun getCoffeeShops(): ArrayList<CoffeeShop> {
        var coffeeShops = DBOjects().coffeeShops
        var query = ParseQuery.getQuery<ParseObject>("MyFavCoffee__Shop")

        // read data from shop db
        query.selectKeys(java.util.List.of("shopId", "shopName", "latitude", "longitude"))
        query.findInBackground { objects: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (obj in objects) {
                    var tempObj = CoffeeShop(
                        obj["shopId"] as Int,
                        obj["shopName"] as String,
                        obj["latitude"] as Double,
                        obj["longitude"] as Double
                    )
                    coffeeShops.add(tempObj);
                }
            } else {
                println("Parse Error: " + e.message)
            }
        }
        return coffeeShops;
    }

    fun getFoods(): ArrayList<Food> {
        var foods = DBOjects().foods
        var query = ParseQuery.getQuery<ParseObject>("MyFavCoffee__Food")

        // read data from food db
        query.selectKeys(java.util.List.of("foodId", "foodName"))
        query.findInBackground { objects: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (obj in objects) {
                    var tempObj = Food(
                        obj["foodId"] as Int,
                        obj["foodName"] as String,
                    )
                    foods.add(tempObj);
                }
            } else {
                println("Parse Error: " + e.message)
            }
        }
        return foods;
    }

    fun getUsers(): ArrayList<User> {
        var users = DBOjects().users
        var query = ParseQuery.getQuery<ParseObject>("MyFavCoffee__User")

        // read data from user db
        query.selectKeys(java.util.List.of("userID", "userName", "password", "email"))
        query.findInBackground { objects: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (obj in objects) {
                    var tempObj = User(
                        obj["userID"] as Int,
                        obj["userName"] as String,
                        obj["password"] as String,
                        obj["email"] as String,
                    )
                    users.add(tempObj);
                }
            } else {
                println("Parse Error: " + e.message)
            }
        }
        return users;
    }

    fun getFavFoods(): ArrayList<FavFood> {
        var favoriteFoods = DBOjects().favoriteFoods
        var query = ParseQuery.getQuery<ParseObject>("MyFavCoffee__FavFood")

        // read data from favFood db
        query.selectKeys(java.util.List.of("userId", "foodId"))
        query.findInBackground { objects: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (obj in objects) {
                    var tempObj = FavFood(
                        obj["userId"] as Int,
                        obj["foodId"] as Int
                    )
                    favoriteFoods.add(tempObj);
                }
            } else {
                println("Parse Error: " + e.message)
            }
        }
        return favoriteFoods;
    }

    fun getFavShops(): ArrayList<FavShop> {
        var favoriteShops = DBOjects().favoriteCoffeeShops
        var query = ParseQuery.getQuery<ParseObject>("MyFavCoffee__favShop")

        // read data from favShop db
        query.selectKeys(java.util.List.of("userId", "shopId"))
        query.findInBackground { objects: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (obj in objects) {
                    var tempObj = FavShop(
                        obj["userId"] as Int,
                        obj["foodId"] as Int
                    )
                    favoriteShops.add(tempObj);
                }
            } else {
                println("Parse Error: " + e.message)
            }
        }
        return favoriteShops;
    }
}

public class CoffeeShop(
     pShopId: Int,
     pShopName: String,
     pLatitude: Double,
     pLongitude: Double
) {
    var ShopId: Int = pShopId
    var ShopName: String = pShopName
    var Latitude: Double = pLatitude
    var Longitude: Double = pLongitude
}

public class Food(pFoodId: Int, pFoodName: String) {
    var FoodId: Int = pFoodId
    var FoodName: String = pFoodName
}

public class User(
     pUserId: Int,
     pUserName: String,
     pEmail: String,
     pPassword: String
) {
    var UserId: Int = pUserId
    var UserName: String = pUserName
    var Email: String = pEmail
    var Password: String = pPassword
}

public class FavFood(var pUserId: Int, var pFoodid: Int) {
    var UserId: Int = pUserId
    var FoodId: Int = pFoodid
}

public class FavShop(var pUserId: Int, var pShopId: Int) {
    var UserId: Int = pUserId
    var ShopId: Int = pShopId
}

public class DBOjects() : AppCompatActivity() {
    var coffeeShops: ArrayList<CoffeeShop> = arrayListOf()
    var foods: ArrayList<Food> = arrayListOf()
    var users: ArrayList<User> = arrayListOf()
    var favoriteFoods: ArrayList<FavFood> = arrayListOf()
    var favoriteCoffeeShops: ArrayList<FavShop> = arrayListOf()
    var sample: String = "this is the sample string"
}