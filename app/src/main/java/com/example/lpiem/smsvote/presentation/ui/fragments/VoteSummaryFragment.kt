package com.example.lpiem.smsvote.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.domain.VoteManager
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryView
import com.example.lpiem.smsvote.presentation.ui.activities.ChartActivity
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersSummaryAdapter
import kotlinx.android.synthetic.main.vote_summary_fragment.*

class VoteSummaryFragment : BaseFragment<VoteSummaryFragmentPresenter>(), VoteSummaryView {

    override val layoutId: Int = R.layout.vote_summary_fragment
    override var presenter: VoteSummaryFragmentPresenter = VoteSummaryFragmentPresenter()

    private val answers: ArrayList<Pair<Int, Pair<Response, Int>>> = ArrayList()
    private val voteManager = VoteManager.instance

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
            voteManager.stopListen()
            activity!!.finish()
        }

        play_btn.setOnClickListener {
            voteManager.listen()
        }

        stop_btn.setOnClickListener {
            voteManager.stopListen()
            voteManager.detachAdapter()
            voteManager.detachFragment()
            val intent = Intent(context, ChartActivity::class.java)
            startActivity(intent)
        }

        question.text = voteManager.vote.question
        textViewNumberOfResponse.text = getString(R.string.numberOfResponse, voteManager.numberOfSmsReceived.toString())

        recyclerViewAnswerSummary.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        for (i in 0 until voteManager.vote.responses.size) {
            answers.add(Pair(i + 1, voteManager.vote.responses[i]))
        }
        val adapter = AnswersSummaryAdapter(answers)
        recyclerViewAnswerSummary.adapter = adapter
        voteManager.attachAdapter(adapter)
        voteManager.attachFragment(this)
    }

    fun notifySmsReceived() {
        textViewNumberOfResponse.text = getString(R.string.numberOfResponse, voteManager.numberOfSmsReceived.toString())
    }

}