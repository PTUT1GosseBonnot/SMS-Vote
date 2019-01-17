package com.example.lpiem.smsvote.presentation.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.domain.VoteManager
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteSummaryView
import com.example.lpiem.smsvote.presentation.ui.activities.ChartActivity
import com.example.lpiem.smsvote.presentation.ui.activities.VoteListActivity
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

        // Button stop hidden by default
        stop_btn.visibility = View.INVISIBLE


        edit_btn.setOnClickListener {
            //activity?.finish()
            val intent = Intent(context, VoteListActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        play_btn.setOnClickListener {
            voteManager.listen()
            // hide play and edit buttons
            it.visibility = View.INVISIBLE
            edit_btn.visibility = View.INVISIBLE
            // show stop button
            stop_btn.visibility = View.VISIBLE
        }

        stop_btn.setOnClickListener {
            voteManager.stopListen()
            // add alert
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setMessage(R.string.alertStopMessage)
                .setCancelable(false)
                .setPositiveButton(R.string.alertStopPositiveButton) { _, _ ->
                    voteManager.detachAdapter()
                    voteManager.detachFragment()
                    val intent = Intent(context, ChartActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .setNegativeButton(R.string.alertStopNegativeButton) { dialog, _ ->
                    dialog.cancel()
                }
                .setTitle(R.string.alertStopTitle)
                .show()
        }

        question.text = voteManager.vote.question
        textViewNumberOfResponse.text = getString(R.string.numberOfResponse, voteManager.numberOfSmsReceived.toString())

        question.text = voteManager.vote.question

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