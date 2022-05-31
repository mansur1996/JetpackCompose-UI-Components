package com.example.compose_example.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class DummyBlogProvider : PreviewParameterProvider<Blog> {
    override val values =
        sequenceOf(
            Blog("Learning Compose", "MindOrks"),
            Blog("Learning Android", "MindOrks")
        )
    override val count: Int = values.count()
}