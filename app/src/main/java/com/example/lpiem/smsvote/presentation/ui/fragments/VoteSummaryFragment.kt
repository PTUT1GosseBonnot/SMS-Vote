package com.example.lpiem.smsvote.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryView
import com.example.lpiem.smsvote.presentation.ui.activities.ChartActivity
import kotlinx.android.synthetic.main.vote_summary_fragment.*

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

        stop_btn.setOnClickListener {
            var intent: Intent = Intent(context, ChartActivity::class.java)
            startActivity(intent)
        }
    }
}