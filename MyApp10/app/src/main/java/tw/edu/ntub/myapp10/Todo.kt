package tw.edu.ntub.myapp10

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

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

@Entity(tableName = "todo_table")
@TypeConverters(TimestampConverter::class)
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false,
    val createdDate: LocalDateTime? = LocalDateTime.now()
)
