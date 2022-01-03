package ru.kiloqky.tipadzen.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.databinding.PostsFragmentBinding
import ru.kiloqky.tipadzen.helpers.extensions.launchWhenCreated
import ru.kiloqky.tipadzen.model.PostModel
import ru.kiloqky.tipadzen.ui.base.BaseFragment
import ru.kiloqky.tipadzen.ui.posts.adapter.PostAdapter
import ru.kiloqky.tipadzen.ui.posts.state.Error
import ru.kiloqky.tipadzen.ui.posts.state.Success

@AndroidEntryPoint
class PostsFragment : BaseFragment(R.layout.posts_fragment) {

    override val actionBarTitleRes: Int by lazy { R.string.posts }
    override val menuRes: Int by lazy { R.menu.menu_posts }

    private val viewModel: PostsViewModel by viewModels()

    private val binding: PostsFragmentBinding by viewBinding()

    private val adapterDataObserver = object :
        RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            if (binding.postsRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE)
                (binding.postsRecycler.layoutManager as LinearLayoutManager)
                    .scrollToPosition(positionStart)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(viewModel) {
            start()
            uiState.onEach {
                when (it) {
                    is Success -> submitPostList(it.list)
                    is Error -> showError(it.throwable.message)
                }
            }.launchWhenCreated(viewLifecycleOwner)
        }
        postAdapter.registerAdapterDataObserver(adapterDataObserver)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private val postAdapter by lazy {
        PostAdapter(viewModel::postClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(postsRecycler) {
                adapter = postAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        postAdapter.unregisterAdapterDataObserver(adapterDataObserver)
    }

    private fun submitPostList(it: List<PostModel>) = postAdapter.submitList(it)


    override fun onOptionsItemSelected(item: MenuItem) = viewModel.onMenuItemClicked(item.itemId)

    companion object {
        fun newInstance() = PostsFragment()
    }
}