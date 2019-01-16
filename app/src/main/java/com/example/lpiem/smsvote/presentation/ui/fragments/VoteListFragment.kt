package com.example.lpiem.smsvote.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.base.BaseFragment
import com.example.lpiem.smsvote.data.entity.ParseVote
import com.example.lpiem.smsvote.data.network.ParseDatabase
import com.example.lpiem.smsvote.presentation.presenter.VoteListFragmentPresenter
import com.example.lpiem.smsvote.presentation.presenter.VoteListView
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseObject



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


        val testObject = ParseObject("TestObject")

        testObject.put("foo", "bar")

        testObject.saveInBackground()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showList()
    }

    fun showList() {
        var query: ParseQuery<ParseVote> = ParseVote.getQuery()

        query.findInBackground { voteList, e ->
            if (e == null) {
                Toast.makeText(context, voteList[0].getVote(), Toast.LENGTH_LONG).show()
            } else {
                showToastError(e)
            }
        }
    }

    override fun showList(list: List<ParseVote>) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    fun showToastError(error: ParseException) {
        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}