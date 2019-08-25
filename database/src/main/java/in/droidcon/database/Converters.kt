package `in`.droidcon.database

import `in`.droidcon.database.entities.Category
import `in`.droidcon.database.entities.Session
import `in`.droidcon.database.entities.Speaker
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlinx.serialization.serializer

class Converters {
    @TypeConverter
    fun listOfLinkToString(data: List<Speaker.Link>): String {
        val json = Json(JsonConfiguration.Stable)
        return json.stringify(Speaker.Link.serializer().list, data)
    }
    @TypeConverter
    fun stringToListOfLink(data: String): List<Speaker.Link> {
        val json = Json(JsonConfiguration.Stable)
        return json.parse(Speaker.Link.serializer().list, data)
    }
    @TypeConverter
    fun qaListToString(data: List<Session.QuestionAnswer>): String {
        val json = Json(JsonConfiguration.Stable)
        return json.stringify(Session.QuestionAnswer.serializer().list, data)
    }
    @TypeConverter
    fun stringToQAList(data: String): List<Session.QuestionAnswer> {
        val json = Json(JsonConfiguration.Stable)
        return json.parse(Session.QuestionAnswer.serializer().list, data)
    }
    @TypeConverter
    fun categoryItemListToString(data: List<Category.Item>): String {
        val json = Json(JsonConfiguration.Stable)
        return json.stringify(Category.Item.serializer().list, data)
    }
    @TypeConverter
    fun stringToCategoryItem(data: String): List<Category.Item> {
        val json = Json(JsonConfiguration.Stable)
        return json.parse(Category.Item.serializer().list, data)
    }
}