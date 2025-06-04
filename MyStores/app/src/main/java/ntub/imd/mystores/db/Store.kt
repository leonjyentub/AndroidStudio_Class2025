package ntub.imd.mystores.db

import android.media.Image
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimestampConverter {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let{
            LocalDateTime.parse(it, formatter)
        }
    }
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }
}

@Entity(tableName = "stores")
@TypeConverters(TimestampConverter::class)
class Store (@PrimaryKey(autoGenerate = true) val uid: Int = 0,
             @ColumnInfo(name = "photo") var photo: Image,
             @ColumnInfo(name = "sname") var sname: String?,
             @ColumnInfo(name = "phone") var phone: String?,
             @ColumnInfo(name = "address") var address: String?,
             @ColumnInfo(name = "score") var score: Int?,
             @ColumnInfo(name = "updatedTime") var mTime: LocalDateTime?)