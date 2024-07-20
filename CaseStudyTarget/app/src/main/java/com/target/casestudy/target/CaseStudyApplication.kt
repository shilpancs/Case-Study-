package com.target.casestudy.target

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CaseStudyApplication: Application() {

    companion object {
        lateinit var instance: CaseStudyApplication
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}