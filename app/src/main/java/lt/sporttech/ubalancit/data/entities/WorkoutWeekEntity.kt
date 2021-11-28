package lt.sporttech.ubalancit.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutWeeks")
data class WorkoutWeekEntity(

    @PrimaryKey
    val position: Int,

)