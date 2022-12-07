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
    @ColumnInfo (name="Alarm") val Alarm:Boolean,
    @ColumnInfo (name="Class") val Class:String

):java.io.Serializable

@Entity
data class Homework(
    @PrimaryKey val hid:Int,
    @ColumnInfo(name = "Title")val Title: String?,
    @ColumnInfo(name="Desc")val Desc: String?,
    @ColumnInfo(name="Diff")val Diff:Int,
    @ColumnInfo(name = "Imp")val Imp:Int,
    @ColumnInfo (name = "Class") val Class: String

):java.io.Serializable
@Entity
data class Goal(
           @PrimaryKey val gid:Int,
                @ColumnInfo (name="Text")val Text:String,
                @ColumnInfo (name="Last")val Last:String,
           @ColumnInfo (name="Class") val Class: String

                )


@Entity
data class Class(
    @PrimaryKey val cls:String,
    @ColumnInfo (name="nbr") val nbr:Int
)


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

@Dao
interface goalDao{
    @Query("SELECT * FROM goal")
    fun getAll():List<Goal>

    @Insert
    fun insert(goal: Goal)

    @Delete
    fun delete(goal: Goal)


}

@Dao
interface classDao{
    @Query("SELECT cls FROM class")
    fun getClasses():List<String>
    @Query ("SELECT * FROM class")
    fun getAll():List<com.example.mindo_kotlin.Class>
    @Delete
    fun delete(Class:com.example.mindo_kotlin.Class)

    @Insert
    fun insert(Class: com.example.mindo_kotlin.Class)

   @Query("UPDATE class SET nbr=(:nbr) WHERE cls=(:cls)")
   fun editClass(nbr: Int,cls: String)
}
@Database(entities = [Course::class,Homework::class,Goal::class,com.example.mindo_kotlin.Class::class], version = 1)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun courseDao():CourseDao
    abstract fun homeworkDao():HomeworkDao
    abstract fun goalDao():goalDao
    abstract fun classDao():classDao

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

