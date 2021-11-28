package lt.sporttech.ubalancit.di

import dagger.Component
import lt.sporttech.ubalancit.features.choosedays.ChooseDaysViewModel
import lt.sporttech.ubalancit.features.exercises.ExercisesListViewModel
import lt.sporttech.ubalancit.features.workout.WorkoutViewModel

@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    fun inject(target: WorkoutViewModel)
    fun inject(target: ExercisesListViewModel)
    fun inject(target: ChooseDaysViewModel)
}