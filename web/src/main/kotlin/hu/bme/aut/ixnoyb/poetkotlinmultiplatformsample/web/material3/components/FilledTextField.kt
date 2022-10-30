package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web.material3.components

import androidx.compose.runtime.Composable
import hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.web.material3.ElementBuilderImplementation
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.TagElement
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement

@Composable
fun FilledTextField(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null,
    content: ContentBuilder<HTMLElement>? = null
) = TagElement(
    elementBuilder = ElementBuilderImplementation("md-filled-text-field"),
    applyAttrs = attrs,
    content = content,
)