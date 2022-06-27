package ru.kiloqky.tipadzen.ui.addpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.databinding.FragmentAddPostBinding
import ru.kiloqky.tipadzen.helpers.methods.extensions.launchWhenCreated
import ru.kiloqky.tipadzen.ui.addpost.state.Error
import ru.kiloqky.tipadzen.ui.addpost.state.Success
import ru.kiloqky.tipadzen.ui.addpost.state.Warning
import ru.kiloqky.tipadzen.ui.base.BaseFragment

@AndroidEntryPoint
class AddPostFragment : BaseFragment(R.layout.fragment_add_post) {

    override val isBackBtnVisible by lazy { true }
    override val actionBarTitleRes by lazy { R.string.add_post }
    override val menuRes by lazy { R.menu.menu_add_post }

    private val binding: FragmentAddPostBinding by viewBinding()
    private val viewModel: AddPostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.uiState.onEach {
            when (it) {
                is Success -> {
                    viewModel.handleSuccess()
                }
                is Error -> {
                    showError(it.throwable.message)
                }
                is Warning -> {
                    showError(it.message)
                }
            }
        }.launchWhenCreated(viewLifecycleOwner)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem) = viewModel.onItemSelected(item.itemId)

    companion object {
        fun newInstance() = AddPostFragment()
    }
}