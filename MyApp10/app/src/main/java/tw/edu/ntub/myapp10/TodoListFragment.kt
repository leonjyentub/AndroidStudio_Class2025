package tw.edu.ntub.myapp10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tw.edu.ntub.myapp10.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {
    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TodoAdapter(
            onItemClick = { todo ->
                val action = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(todo.id)
                findNavController().navigate(action)
            },
            onTodoCheckedChange = { todo, isChecked ->
                val updatedTodo = todo.copy(isCompleted = isChecked)
                todoViewModel.update(updatedTodo)
            }
        )

        binding.todoRecyclerView.adapter = adapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(context)

        todoViewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            adapter.submitList(todos)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}