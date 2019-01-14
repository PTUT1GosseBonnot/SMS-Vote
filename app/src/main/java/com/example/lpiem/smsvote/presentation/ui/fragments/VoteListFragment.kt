package com.example.lpiem.smsvote.presentation.ui.fragments

import android.os.Bundle
import android.widget.Toast
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.data.entity.ParseVote
import com.example.lpiem.smsvote.data.network.ParseDatabase
import com.example.lpiem.smsvote.presentation.presenter.VoteListFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteListView

class VoteListFragment : BaseFragment<VoteListFragmentPresenter>(), VoteListView {

    override val layoutId: Int = R.layout.vote_list_fragment
    override var presenter: VoteListFragmentPresenter = VoteListFragmentPresenter()


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
        ParseDatabase().getAllVotesFromParse()
    }

    override fun showList(list: List<ParseVote>) {
        Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show()
    }

    fun showToastError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}