package com.renzard.superherosquadmaker.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//Coroutined Scoped Fragment in order
abstract class ScopedFragment : Fragment(), CoroutineScope {
    private lateinit var job: Job

    //Dispacters Main because its updating the ui
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    //created the Job on Oncreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    //whenever the fragment inheriting is destroyed the job is cancelled
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}