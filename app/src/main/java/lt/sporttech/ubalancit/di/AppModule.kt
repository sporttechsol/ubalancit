package lt.sporttech.ubalancit.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val appContext: Context, ) {

    @Provides
    fun provideContext(): Context = appContext
}