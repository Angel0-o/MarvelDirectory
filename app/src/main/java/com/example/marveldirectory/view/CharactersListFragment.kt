package com.example.marveldirectory.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldirectory.R
import com.example.marveldirectory.databinding.FragmentCharactersListBinding
import com.example.marveldirectory.model.data.DataCharacter
import com.example.marveldirectory.viewmodel.CharactersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersListFragment : Fragment() {
    private lateinit var binding:FragmentCharactersListBinding
    private val characterViewModel: CharactersViewModel by viewModels()
    private val heroesList: MutableList<DataCharacter> = mutableListOf()
    private var isLoading = false
    private var limit:Int = 20
    private var offset:Int = 0

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharactersListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharactersListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersListBinding.bind(view)

        characterViewModel.getCharacters(1,limit, offset)
        characterViewModel.charactersLiveData.observe(viewLifecycleOwner, Observer {
            if (it.data.results.isNotEmpty()){
                Log.i("MMGLog","${it.data.results.size}")
                heroesList.addAll(it.data.results)
                binding.charactersRecyclerView.adapter = CharactersListAdapter(heroesList){dataCharacter ->
                    onItemListener(dataCharacter) }
                isLoading = false
            }
        })
        binding.charactersRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager =binding.charactersRecyclerView.layoutManager as LinearLayoutManager
                val totalItems = binding.charactersRecyclerView.adapter?.itemCount
                if (dy>0 && !isLoading){
                    Log.i("Positions","${layoutManager.findLastCompletelyVisibleItemPosition()}.${totalItems}")
                    if (layoutManager.findLastVisibleItemPosition()>= totalItems?.minus(1)!!){
                        offset = totalItems
                        isLoading = true
                        characterViewModel.getCharacters(1,limit, offset)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun onItemListener(dataCharacter: DataCharacter) {
        val args:Bundle = Bundle().apply {
            putSerializable("hero",dataCharacter)
        }
        NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_charactersListFragment_to_characterDetailFragment,args)
    }
}