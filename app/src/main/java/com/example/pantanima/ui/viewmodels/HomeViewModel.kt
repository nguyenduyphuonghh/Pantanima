package com.example.pantanima.ui.viewmodels

import com.example.pantanima.ui.activities.NavActivity
import java.lang.ref.WeakReference

class HomeViewModel(activity: WeakReference<NavActivity>) : BaseViewModel(activity) {

    val title = "This is home Fragment"

}