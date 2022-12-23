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
    @ColumnInfo(name="Class") val Class: String


):java.io.Serializable

@Entity
data class Task(
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
data class Resources(
    @PrimaryKey val rid:Int,
    @ColumnInfo (name="type") val Type:String,
    @ColumnInfo (name="val") val Val:String,
    @ColumnInfo(name="Class") val Class: String
)

@Entity
data class Class(
    @PrimaryKey val cls:String,
)


@Dao
interface CourseDao{
    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Query("SELECT * FROM course WHERE Class IN (:courseClass) ")
    fun loadByClass(courseClass:String): List<Course>



    @Insert
    fun insert (vararg course: Course)

    @Delete
    fun delete(user: Course)


}

@Dao
interface TaskkDao{
    @Query("SELECT * FROM task")
    fun getAll():List<Task>

    @Query("SELECT * FROM task WHERE Class IN (:taskClass) ")
    fun loadByClass(taskClass:String): List<Task>

    @Insert
    fun insert(homework: Task)

    @Delete
    fun delete(homework: Task)


}

@Dao
interface goalDao{
    @Query("SELECT * FROM goal")
    fun getAll():List<Goal>

    @Query("SELECT * FROM goal WHERE Class IN (:goalClass) ")
    fun loadByClass(goalClass:String): List<Goal>

    @Insert
    fun insert(goal: Goal)

    @Delete
    fun delete(goal: Goal)


}
@Dao
interface resourceDao {
    @Query("SELECT * FROM resources")
    fun getAll(): List<Resources>
    @Query("SELECT * FROM resources WHERE Class IN (:resourceClass) ")
    fun loadByClass(resourceClass:String): List<Resources>
    @Insert
    fun insert(resources: Resources)

    @Delete
    fun delete(resources: Resources)
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

   /*@Query("UPDATE class SET nbr=(:nbr) WHERE cls=(:cls)")
   fun editClass(nbr: Int,cls: String)*/
}
@Database(entities = [Course::class,Task::class,Goal::class,Resources::class,com.example.mindo_kotlin.Class::class], version = 1)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun courseDao():CourseDao
    abstract fun homeworkDao():TaskkDao
    abstract fun goalDao():goalDao
    abstract fun resourceDao():resourceDao
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

