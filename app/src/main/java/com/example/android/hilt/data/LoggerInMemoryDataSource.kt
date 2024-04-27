package com.example.android.hilt.data

import dagger.hilt.android.scopes.ActivityScoped
import java.util.LinkedList
import javax.inject.Inject

@ActivityScoped // we want to scope it to the Activity container because we want it to only exist
// as long as the Activity exists
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource {
    private val logs = LinkedList<Log>()

    override fun addLog(msg: String) {
        logs.add(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        logs.clear()
    }
}