package com.example.lpiem.smsvote.presentation.ui.fragments

import android.os.Bundle
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryView

class VoteSummaryFragment: BaseFragment<VoteSummaryFragmentPresenter>(), VoteSummaryView {

    override val layoutId: Int = R.layout.vote_summary_fragment
    override var presenter: VoteSummaryFragmentPresenter = VoteSummaryFragmentPresenter()

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
        presenter.attach(this)
    }
}