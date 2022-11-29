package com.example.mindo_kotlin

import android.content.Context
import androidx.room.*


@Entity
data class Course(
    @PrimaryKey  val cid:Int,

    @ColumnInfo (name="Title") val Title:String?,
    @ColumnInfo (name="Desc") val Desc:String?,
    @ColumnInfo (name="TimeStar") val TimeStart:String?,
    @ColumnInfo (name="TimeEnd") val TimeEnd:String?,
    @ColumnInfo (name="Imp") val Imp:Int,
    @ColumnInfo (name="Alarm") val Alarm:Boolean

):java.io.Serializable

@Entity
data class Homework(
    @PrimaryKey val hid:Int,
    @ColumnInfo(name = "Title")val Title: String?,
    @ColumnInfo(name="Desc")val Desc: String?,
    @ColumnInfo(name="Diff")val Diff:Int,
    @ColumnInfo(name = "Imp")val Imp:Int

):java.io.Serializable


@Dao
interface CourseDao{
    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Query("SELECT * FROM course WHERE cid IN (:courseId) LIMIT 1")
    fun loadById(courseId: Int): Course

    @Insert
    fun insert (vararg course: Course)

    @Delete
    fun delete(user: Course)


}

@Dao
interface HomeworkDao{
    @Query("SELECT * FROM homework")
    fun getAll():List<Homework>

    @Insert
    fun insert(homework: Homework)

    @Delete
    fun delete(homework: Homework)


}
@Database(entities = [Course::class,Homework::class], version = 1)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun courseDao():CourseDao
    abstract fun homeworkDao():HomeworkDao

    companion object DataBase{
        fun getInstance(c: Context) :AppDatabase{
            var db: AppDatabase? =null
            if(db==null){
            db=Room.databaseBuilder(
            c,
            AppDatabase::class.java,
            "main_database"
        ).allowMainThreadQueries().build()}
            return db
            }
    }


    }

