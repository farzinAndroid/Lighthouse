package pro.sparrow_team.lighthouse.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pro.sparrow_team.lighthouse.data.datstore.DataStoreRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepoImpl(@ApplicationContext context: Context) =
        DataStoreRepoImpl(context)

}