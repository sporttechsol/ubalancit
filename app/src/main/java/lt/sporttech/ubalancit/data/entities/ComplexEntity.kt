package lt.sporttech.ubalancit.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = WorkoutDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["dayId"],
            onDelete = CASCADE
        )
    ]
)
data class ComplexEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val dayId: Int,

    val title: String,
)