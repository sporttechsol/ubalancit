package lt.sporttech.ubalancit.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "WorkoutDays",

    foreignKeys = [
        ForeignKey(
            entity = WorkoutWeekEntity::class,
            parentColumns = ["position"],
            childColumns = ["weekPosition"],
        )
    ],

    indices = [
        Index("dayPosition", unique = true)
    ]
)
data class WorkoutDayEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val weekPosition: Int,

    val dayPosition: Int,
)