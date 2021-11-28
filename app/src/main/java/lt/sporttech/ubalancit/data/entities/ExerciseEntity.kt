package lt.sporttech.ubalancit.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ComplexEntity::class,
            parentColumns = ["id"],
            childColumns = ["complexId"],
            onDelete = CASCADE,
        )
    ]
)
data class ExerciseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val complexId: Int,

    val title: String,
)