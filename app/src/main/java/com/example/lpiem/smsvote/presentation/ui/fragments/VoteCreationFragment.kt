package com.example.lpiem.smsvote.presentation.ui.fragments

import android.Manifest
import android.os.Bundle
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.base.BasePresenter
import com.example.lpiem.smsvote.base.BaseView
import com.example.lpiem.smsvote.presentation.presenter.VoteCreationFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteCretionView
import com.example.lpiem.smsvote.utils.PermisionUtil

class VoteCreationFragment : BaseFragment<VoteCreationFragmentPresenter>(), VoteCretionView {

    override val layoutId: Int = R.layout.vote_creation_fragment
    lateinit var presenter: BasePresenter<BaseView>

    override fun displayLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = VoteCreationFragmentPresenter()
        presenter.attach(this)
        PermisionUtil.askForPermission(activity!!, Manifest.permission.RECEIVE_SMS, 15)
    }

}