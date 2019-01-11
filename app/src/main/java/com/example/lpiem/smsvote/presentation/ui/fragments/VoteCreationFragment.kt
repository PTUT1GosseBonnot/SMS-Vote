package com.example.lpiem.smsvote.presentation.ui.fragments

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.domain.VoteManager
import com.example.lpiem.smsvote.presentation.presenter.VoteCreationFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteCretionView
import com.example.lpiem.smsvote.presentation.ui.activities.VoteSummaryActivity
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersAdapter
import com.example.lpiem.smsvote.utils.PermisionUtil
import kotlinx.android.synthetic.main.vote_creation_fragment.*

class VoteCreationFragment : BaseFragment<VoteCreationFragmentPresenter>(), VoteCretionView {

    override val layoutId: Int = R.layout.vote_creation_fragment
    override var presenter: VoteCreationFragmentPresenter = VoteCreationFragmentPresenter()

    private val answers: ArrayList<Response> = ArrayList()

    override fun goToSummary() {
        val intent = Intent(context, VoteSummaryActivity::class.java)
        startActivity(intent)
    }

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
        PermisionUtil.askForPermission(activity!!, Manifest.permission.RECEIVE_SMS, 15)
        recyclerViewAnswers.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val voteManager: VoteManager = VoteManager.instance
        for (i in 0 until 2) {
            answers.add(Response(answers.size + 1, null))
        }
        val adapter = AnswersAdapter(answers)
        recyclerViewAnswers.adapter = adapter
        addAnswerButton.setOnClickListener {
            if (answers.none { response ->
                    response.response.isNullOrBlank()
                }) {
                answers.add(Response(answers.size + 1, null))
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context,R.string.mustFillAllAnswers,Toast.LENGTH_SHORT).show()
            }
        }
        validateButton.setOnClickListener {
            if (answers.none { response ->
                    response.response.isNullOrBlank()
                } && !questionEditText.text.isNullOrBlank()) {
                voteManager.setQuestion(questionEditText.text.toString())
                voteManager.clear()
                voteManager.addAnswers(answers)
                goToSummary()
            } else {
                Toast.makeText(context, R.string.mustFillAllFields, Toast.LENGTH_SHORT).show()
            }
        }
    }

}