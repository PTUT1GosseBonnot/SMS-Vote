package com.example.lpiem.smsvote.presentation.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.entity.Response
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryView
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersAdapter
import kotlinx.android.synthetic.main.vote_creation_fragment.*
import kotlinx.android.synthetic.main.vote_summary_fragment.*

class VoteSummaryFragment: BaseFragment<VoteSummaryFragmentPresenter>(), VoteSummaryView {

    override val layoutId: Int = R.layout.vote_summary_fragment
    override var presenter: VoteSummaryFragmentPresenter = VoteSummaryFragmentPresenter()

    private val answers: ArrayList<Response> = ArrayList()

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

        edit_btn.setOnClickListener {
            activity!!.finish()
        }

        play_btn.setOnClickListener {
            Toast.makeText(context, R.string.playBtnClicked,Toast.LENGTH_SHORT).show()

        }

        stop_btn.setOnClickListener {
            Toast.makeText(context, R.string.stopBtnClicked, Toast.LENGTH_SHORT).show()
        }

        recyclerViewAnswerSummary.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        for (i in 0 until 2) {
            answers.add(Response(answers.size + 1, null))
        }
        val adapter = AnswersAdapter(answers)
        recyclerViewAnswerSummary.adapter = adapter

    }


}