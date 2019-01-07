package com.example.lpiem.smsvote.base

interface BaseView {

    fun displayLoader()

    fun hideLoader()

    fun showError(throwable: Throwable)

}