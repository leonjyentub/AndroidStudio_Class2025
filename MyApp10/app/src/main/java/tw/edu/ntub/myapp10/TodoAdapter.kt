package tw.edu.ntub.myapp10

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.edu.ntub.myapp10.databinding.TodoItemBinding

class TodoAdapter (private val onItemClick: (Todo) -> Unit,
                   private val onTodoCheckedChange: (todo: Todo, isChecked: Boolean)->Unit):
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {
    private val TAG = "TodoAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(todo: Todo) {
            binding.todoTitle.text = todo.title
            binding.todoDescription.text = todo.description
            binding.todoCheckbox.isChecked = todo.isCompleted
            // 先移除先前的 Listener 避免重複觸發
            binding.todoCheckbox.setOnCheckedChangeListener(null)
            binding.todoCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                onTodoCheckedChange(todo, isChecked)
                Log.d(TAG, "bind: ${todo.isCompleted}")
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}