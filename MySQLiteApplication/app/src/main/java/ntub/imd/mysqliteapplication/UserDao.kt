package ntub.imd.mysqliteapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert fun insert(user: User): Long

    @Update fun update(user: User): Int //成功的話可以知道更新了幾筆

    @Delete fun delete(user: User)

    @Query("SELECT * FROM users") fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun getUsersByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE uid =:userId")
    fun getUserById(userId: Int): User?
}