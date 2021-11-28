package lt.sporttech.ubalancit.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import lt.sporttech.ubalancit.data.entities.WorkoutWeekEntity

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrUpdate(weeks: List<WorkoutWeekEntity>)
}