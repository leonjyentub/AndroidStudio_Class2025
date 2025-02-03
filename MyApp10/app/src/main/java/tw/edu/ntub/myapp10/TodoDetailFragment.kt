package tw.edu.ntub.myapp10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import tw.edu.ntub.myapp10.databinding.FragmentTodoDetailBinding

class TodoDetailFragment : Fragment() {
    private var _binding: FragmentTodoDetailBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel: TodoViewModel by viewModels()
    private val args: TodoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load todo details
        todoViewModel.getTodoById(args.todoId).observe(viewLifecycleOwner) { todo ->
            todo?.let {
                binding.titleEdit.setText(it.title)
                binding.descriptionEdit.setText(it.description)
            }
        }

        binding.saveButton.setOnClickListener {
            val title = binding.titleEdit.text.toString()
            val description = binding.descriptionEdit.text.toString()

            if (title.isNotEmpty()) {
                val updatedTodo = Todo(
                    id = args.todoId,
                    title = title,
                    description = description
                )
                todoViewModel.update(updatedTodo)

                Snackbar.make(view, "Todo updated successfully", Snackbar.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Snackbar.make(view, "Title cannot be empty", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
