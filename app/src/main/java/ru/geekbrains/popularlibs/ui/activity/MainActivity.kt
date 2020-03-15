package ru.geekbrains.popularlibs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.popularlibs.R
import ru.geekbrains.popularlibs.mvp.model.CountersModel
import ru.geekbrains.popularlibs.mvp.presenter.MainPresenter
import ru.geekbrains.popularlibs.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this, CountersModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners(){
        btn_counter1.setOnClickListener{
            presenter.firstCounterClick()
        }

        btn_counter2.setOnClickListener{
            presenter.secondCounterClick()
        }

        btn_counter3.setOnClickListener{
            presenter.thirdCounterClick()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putIntArray("counters", counters.toIntArray())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        savedInstanceState.getIntArray("counters")?.toList()?.let {
//            counters.clear()
//            counters.addAll(it)
//        }
    }

    override fun setFistButtonText(text: String) {
        btn_counter1.text = text
    }

    override fun setSecondButtonText(text: String) {
        btn_counter2.text = text
    }

    override fun setThirdButtonText(text: String) {
        btn_counter3.text = text
    }
}
