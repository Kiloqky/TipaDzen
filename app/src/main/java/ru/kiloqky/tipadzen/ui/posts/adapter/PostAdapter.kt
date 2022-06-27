package ru.kiloqky.tipadzen.ui.posts.adapter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kiloqky.tipadzen.databinding.ItemPostBinding
import ru.kiloqky.tipadzen.helpers.methods.extensions.click
import ru.kiloqky.tipadzen.helpers.methods.extensions.toDateFormat
import ru.kiloqky.tipadzen.model.PostModel

class PostAdapter(private val onItemClicked: (post: PostModel) -> Unit) :
    ListAdapter<PostModel, PostAdapter.ViewHolder>(MATCHER) {

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.click {
                if (binding.text.maxLines == 3) {
                    openTextAnimator.start()
                    openTitleAnimator.start()
                } else {
                    closeTextAnimator.start()
                    closeTitleAnimator.start()
                }
            }
        }

        private val openTextAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.text,
            PropertyValuesHolder.ofInt("maxLines", 15),
            PropertyValuesHolder.ofFloat("textSize", 14f, 16f)
        ).setDuration(150)

        private val openTitleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.title,
            PropertyValuesHolder.ofInt("maxLines", 5),
            PropertyValuesHolder.ofFloat("textSize", 18f, 16f)
        ).setDuration(150)


        private val closeTextAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.text,
            PropertyValuesHolder.ofInt("maxLines", 3),
            PropertyValuesHolder.ofFloat("textSize", 16f, 14f)
        ).setDuration(150)

        private val closeTitleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            binding.title,
            PropertyValuesHolder.ofInt("maxLines", 1),
            PropertyValuesHolder.ofFloat("textSize", 16f, 18f)
        ).setDuration(150)

        fun bind(post: PostModel) {
            binding.text.text = post.text
            binding.title.text = post.title
            binding.date.text = post.date.toDateFormat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val MATCHER = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel) =
                oldItem.sha == newItem.sha

            override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel) =
                oldItem.text == newItem.text &&
                        oldItem.title == newItem.title &&
                        oldItem.date == newItem.date

        }
    }
}