package com.example.di

import com.example.fiicodenou.features.data.repository.AuthRepository
import com.example.fiicodenou.features.data.repository.FoodRepository
import com.example.fiicodenou.features.data.repository.TrackedFoodRepository
import com.example.fiicodenou.features.domain.models.Realm_Objects.FoodApiLocal
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedFood
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedHour
import com.example.fiicodenou.features.domain.models.Realm_Objects.TrackedUser
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Exercise
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.WorkoutUser
import com.example.fiicodenou.features.domain.repository.AuthRepositoryIMPL
import com.example.fiicodenou.features.domain.repository.FoodRepositoryIMPL
import com.example.fiicodenou.features.domain.repository.TrackedFoodRepositoryIMPL
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryIMPL(
        auth = Firebase.auth,
        fb = FirebaseFirestore.getInstance()
    )

    @Provides
    fun provideFoodRepository(): FoodRepository = FoodRepositoryIMPL(
        fb = FirebaseFirestore.getInstance()
    )

    @Provides
    fun provideRealmDatabase(): TrackedFoodRepository = TrackedFoodRepositoryIMPL(
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    TrackedFood::class,
                    TrackedUser::class,
                    TrackedHour::class,
                    WorkoutUser::class,
                    Workout::class,
                    Exercise::class,
                    FoodApiLocal::class
                )
            )
        )
    )
}
