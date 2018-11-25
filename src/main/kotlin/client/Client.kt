package client

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    HttpClient(Apache).use {
        when {
            args.isEmpty() -> queryTasks(it)
            args.isNotEmpty() -> addTasks(args, it)
        }

    }
}

private fun addTasks(args: Array<String>, client: HttpClient) {
    runBlocking {
        args.forEach { task ->
            client.post<String>("http://localhost:8080/tasks") { body = task }
        }
    }
}

private fun queryTasks(client: HttpClient) {
    runBlocking {
        client.get<String>("http://localhost:8080/tasks")
    }.let {tasks ->
        print(tasks)
    }
}