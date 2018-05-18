package template

import data.User
import io.ktor.html.Placeholder
import io.ktor.html.PlaceholderItem
import io.ktor.html.Template
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.h1

class HomeTemplate: Template<HTML> {
    override fun HTML.apply() {
        body {
            h1 {
                + "Hello world!"
            }
        }
    }
}

class TestTemplate: Template<FlowContent> {
    val users = Placeholder<User>()
    override fun FlowContent.apply() {
    }

}