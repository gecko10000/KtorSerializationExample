package gecko10000.kse

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    val r1 = RecursiveObject("hello")
    val r2 = RecursiveObject("world", parent = r1)
    r1.children.add(r2)
    embeddedServer(Netty, port = 10000) {
        routing {
            get("r1") {
                call.respond(r1)
            }
            get("r1children") {
                call.respond(r1.children)
            }
            get("r1childrenjson") {
                call.respondText(Json.encodeToString(r1.children), contentType = ContentType.Application.Json)
            }
        }
        install(ContentNegotiation) {
            json(Json)
        }
    }.start(wait = true)
}
