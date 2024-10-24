package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    lateinit var dieViewModel: DieViewModel

    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var rollValue: Int = 0
    val KEY = "mykey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]

        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getDieRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }

        if(dieViewModel.getDieRoll().value == null){
            throwDie()
        }

    }



    fun throwDie() {
        dieViewModel.setDieRoll(Random.nextInt(1, dieSides))
    }

    companion object{
        fun newInstance(sides: Int) = DieFragment().apply{
            arguments = Bundle().apply { putInt(DIESIDE, sides) }
        }
    }
}