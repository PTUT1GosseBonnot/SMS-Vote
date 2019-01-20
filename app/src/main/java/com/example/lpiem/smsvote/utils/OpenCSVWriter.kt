package com.example.lpiem.smsvote.utils

import com.example.lpiem.smsvote.R
import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.lpiem.smsvote.domain.VoteManager
import com.opencsv.CSVWriter
import java.io.FileWriter
import java.io.IOException
import java.util.*


class OpenCSVWriter {

    var context: Context? = null

    var csv = Environment.getExternalStorageDirectory().getAbsolutePath() + "/smsVote.csv"
    val voteManager: VoteManager = VoteManager.instance

    constructor(context: Context?) {
        this.context = context
    }

    fun createCSV() {
        var writer: CSVWriter? = null
        try {
            Log.d("writeCSV",csv)
            writer = CSVWriter(FileWriter(csv))

            val data = ArrayList<Array<String>>()
            data.add(arrayOf("Question","Reponse","Nombre de réponses"))
            for(vote in voteManager.vote.responses) {
                data.add(arrayOf(voteManager.vote.question.toString(),vote.first.response.toString(),vote.second.toString()))
            }



            writer.writeAll(data) // data is adding to csv

            writer.close()

            Toast.makeText(context,R.string.saveCsvSuccess,Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(context,R.string.saveCsvFailed,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}
