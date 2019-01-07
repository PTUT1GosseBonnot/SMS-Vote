package com.example.lpiem.smsvote.base

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment

abstract class BaseFragment<out P : BasePresenter<BaseView>> : Fragment(), BaseView {

    @get:LayoutRes
    protected abstract val layoutId: Int
}