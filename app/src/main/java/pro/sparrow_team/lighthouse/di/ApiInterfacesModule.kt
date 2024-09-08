package pro.sparrow_team.lighthouse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.sparrow_team.lighthouse.data.remote.MainApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiInterfacesModule {

    @Provides
    @Singleton
    fun provideMainScreenApiInterface(retrofit: Retrofit): MainApiInterface =
        retrofit.create(MainApiInterface::class.java)

}