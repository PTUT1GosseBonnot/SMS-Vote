package com.example.lpiem.smsvote.presentation.ui.fragments

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.entity.Response
import com.example.lpiem.smsvote.presentation.presenter.VoteCreationFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteCretionView
import com.example.lpiem.smsvote.presentation.ui.activities.VoteCreationActivity
import com.example.lpiem.smsvote.presentation.ui.activities.VoteSummaryActivity
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersAdapter
import com.example.lpiem.smsvote.utils.PermisionUtil
import kotlinx.android.synthetic.main.vote_creation_fragment.*

class VoteCreationFragment : BaseFragment<VoteCreationFragmentPresenter>(), VoteCretionView {

    override val layoutId: Int = R.layout.vote_creation_fragment
    override var presenter: VoteCreationFragmentPresenter = VoteCreationFragmentPresenter()

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
        PermisionUtil.askForPermission(activity!!, Manifest.permission.RECEIVE_SMS, 15)
        recyclerViewAnswers.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
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
            }
        }
        validateButton.setOnClickListener {
            if (answers.none { response ->
                    response.response.isNullOrBlank()
                } && !questionEditText.text.isNullOrBlank()) {
                val intent = Intent(context, VoteSummaryActivity::class.java)
                startActivity(intent)
            }
        }
    }

}