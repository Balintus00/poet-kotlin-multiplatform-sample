package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web.material3

import kotlinx.browser.document
import org.jetbrains.compose.web.dom.ElementBuilder
import org.w3c.dom.Element

// This is a copy of the private class org.jetbrains.compose.web.dom.ElementBuilderImplementation
internal class ElementBuilderImplementation<TElement: Element>(private val tagName: String) : ElementBuilder<TElement> {
    private val element: Element by lazy {
        document.createElement(tagName)
    }

    override fun create(): TElement = element.cloneNode() as TElement
}
