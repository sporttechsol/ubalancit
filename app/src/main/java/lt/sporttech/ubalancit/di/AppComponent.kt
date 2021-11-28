package lt.sporttech.ubalancit.di

import dagger.Component
import lt.sporttech.ubalancit.features.workout.WorkoutViewModel

@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    fun inject(target: WorkoutViewModel)
}