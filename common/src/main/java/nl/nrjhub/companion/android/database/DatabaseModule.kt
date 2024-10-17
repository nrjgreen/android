package nl.nrjhub.companion.android.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nl.nrjhub.companion.android.database.authentication.AuthenticationDao
import nl.nrjhub.companion.android.database.location.LocationHistoryDao
import nl.nrjhub.companion.android.database.notification.NotificationDao
import nl.nrjhub.companion.android.database.qs.TileDao
import nl.nrjhub.companion.android.database.sensor.SensorDao
import nl.nrjhub.companion.android.database.server.ServerDao
import nl.nrjhub.companion.android.database.settings.SettingsDao
import nl.nrjhub.companion.android.database.wear.CameraTileDao
import nl.nrjhub.companion.android.database.wear.EntityStateComplicationsDao
import nl.nrjhub.companion.android.database.wear.FavoriteCachesDao
import nl.nrjhub.companion.android.database.wear.FavoritesDao
import nl.nrjhub.companion.android.database.widget.ButtonWidgetDao
import nl.nrjhub.companion.android.database.widget.CameraWidgetDao
import nl.nrjhub.companion.android.database.widget.MediaPlayerControlsWidgetDao
import nl.nrjhub.companion.android.database.widget.StaticWidgetDao
import nl.nrjhub.companion.android.database.widget.TemplateWidgetDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideAuthenticationDao(database: AppDatabase): AuthenticationDao =
        database.authenticationDao()

    @Provides
    fun provideSensorDao(database: AppDatabase): SensorDao = database.sensorDao()

    @Provides
    fun provideButtonWidgetDao(database: AppDatabase): ButtonWidgetDao = database.buttonWidgetDao()

    @Provides
    fun provideCameraWidgetDao(database: AppDatabase): CameraWidgetDao = database.cameraWidgetDao()

    @Provides
    fun provideMediaPlayCtrlWidgetDao(database: AppDatabase): MediaPlayerControlsWidgetDao =
        database.mediaPlayCtrlWidgetDao()

    @Provides
    fun provideStaticWidgetDao(database: AppDatabase): StaticWidgetDao = database.staticWidgetDao()

    @Provides
    fun provideTemplateWidgetDao(database: AppDatabase): TemplateWidgetDao =
        database.templateWidgetDao()

    @Provides
    fun provideLocationHistoryDao(database: AppDatabase): LocationHistoryDao = database.locationHistoryDao()

    @Provides
    fun provideNotificationDao(database: AppDatabase): NotificationDao = database.notificationDao()

    @Provides
    fun provideTileDao(database: AppDatabase): TileDao = database.tileDao()

    @Provides
    fun provideFavoritesDao(database: AppDatabase): FavoritesDao = database.favoritesDao()

    @Provides
    fun provideFavoriteCachesDao(database: AppDatabase): FavoriteCachesDao = database.favoriteCachesDao()

    @Provides
    fun provideServerDao(database: AppDatabase): ServerDao = database.serverDao()

    @Provides
    fun provideSettingsDao(database: AppDatabase): SettingsDao = database.settingsDao()

    @Provides
    fun provideCameraTileDao(database: AppDatabase): CameraTileDao = database.cameraTileDao()

    @Provides
    fun provideEntityStateComplicationsDao(database: AppDatabase): EntityStateComplicationsDao = database.entityStateComplicationsDao()
}
