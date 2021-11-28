package lt.sporttech.ubalancit.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import lt.sporttech.ubalancit.core.alias.Milliseconds

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = CASCADE,
        )
    ]
)
data class SetEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val exerciseId: Int,

    val title: String,

    val repeats: Int,

    val time: Milliseconds?,
)