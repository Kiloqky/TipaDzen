package ru.kiloqky.tipadzen.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzencompose.ui.theme.TipaDzenComposeTheme

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.posts_fragment

            viewModel.start()

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setContent {
                TipaDzenComposeTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        PostsScreen()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun PostsScreen() {
        Column(Modifier.padding(all = 16.dp)) {
            AddPostCard()
            Posts()
        }
    }

    @Composable
    private fun AddPostCard() {
        var text by remember { mutableStateOf("") }

        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                Modifier.weight(90f, true)
            )
            Button(
                onClick = {
                    viewModel.addPost(text)
                },
                Modifier
                    .weight(10f, true)
                    .padding(8.dp)

            ) {
                Text(text = "Add")
            }
        }
    }

    @Composable
    fun Posts() {
        val res = viewModel.postsFlow.collectAsState(initial = listOf())

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(res.value.asReversed()) { post ->
                Text(
                    text = post.text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            shape = RectangleShape,
                            color = MaterialTheme.colors.background
                        )
                        .fillMaxWidth()
                        .border(1.dp, color = MaterialTheme.colors.primary, shape = RectangleShape)
                )
            }
        }
    }

    companion object {
        fun newInstance() = PostsFragment()
    }
}