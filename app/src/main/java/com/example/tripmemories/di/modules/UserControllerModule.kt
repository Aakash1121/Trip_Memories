package com.example.tripmemories.di.modules

import com.example.tripmemories.controller.UserController
import com.example.tripmemories.controller.UserControllerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@InstallIn(ActivityComponent::class)
@Module
class UserControllerModule {


    @Provides
    fun providesUserController():UserController{
        return UserControllerImpl()
    }

}