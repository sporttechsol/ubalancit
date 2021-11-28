package lt.sporttech.ubalancit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lt.sporttech.ubalancit.data.entities.*

@Database(
    entities = [
        WorkoutWeekEntity::class,
        WorkoutDayEntity::class,
        ComplexEntity::class,
        ExerciseEntity::class,
        SetEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class WorkoutDatabase: RoomDatabase() {

    companion object {
        private var instance: WorkoutDatabase? = null

        fun provide(
            context: Context
        ): WorkoutDatabase = instance ?: synchronized(this) {
            instance ?: buildInstance(context).also {
                instance = it
            }
        }

        private fun buildInstance(
            context: Context
        ): WorkoutDatabase = Room
            .databaseBuilder(context.applicationContext, WorkoutDatabase::class.java, "workout.db")
            .build()
    }
}