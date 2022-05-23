package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.fragment.EndPage
import com.example.myapplication.presentation.fragment.FourImageFragment
import com.example.myapplication.presentation.fragment.StartPage

class MainActivity : AppCompatActivity() {
    companion object {
        const val FRAGMENT_CONTAINER_ID = R.id.fragment_container
    }

    private lateinit var endPage : Fragment
    private lateinit var startPage : Fragment

    // Создаваемый лист вопросов
    private val tasksList : MutableList<Fragment> = fillTasksList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startPage = StartPage(object : StartPage.OnGoClickListener {
            override fun onGoClick() {
                goToFragment(tasksList[0])
            }
        })
        addFragment(startPage)

    }


    // Метод перехода между фрагментами
    private fun goToFragment( fragment : Fragment) =
        supportFragmentManager.beginTransaction().replace(FRAGMENT_CONTAINER_ID, fragment).commit()

    // Метод добавления фрагмента (Нужен только в самом начале)
    private fun addFragment( fragment : Fragment) =
        supportFragmentManager.beginTransaction().add(FRAGMENT_CONTAINER_ID, fragment).commit()

    // Метод удаления фрагмента, пока нигде не использован, но будет использоваться при
    // Нажатии на крестик во фрагменте с вопросом
    private fun removeFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().remove(fragment).commit()


    /**
     * Функция заполнения массива с вопросами
     */
    private fun fillTasksList() : MutableList<Fragment> {
        // лист, который мы вернём по выполнению функции
        val result = mutableListOf<Fragment>()

        // добавляем 6 вопросов (можно изменить значение, не суть, для примера 5)
        for (step in 0..5) {
            // Добавляем пустые фрагменты
            val addedFragment = Fragment()
            result.add(addedFragment)
        }

        // количество всех ответов
        // да, на самом деле это количество вопросов,
        // но эта переменная будет использована в конце, когда количество вопросов
        // будет равно количеству ответов

        val countOfAllAnswers = result.size

        // количество правильных ответов
        var countOfCurrentAnswers = 0

        // цикл
        result.forEachIndexed {
            // лямбда выражение, чтобы иметь доступ к индексу
                index, _ ->
            result[index] = FourImageFragment(

                // Даём вопросу номер, +1 для того, чтобы счёт начинался с 1
                index + 1,

                result.size,

                // Присвоение слушателя нажатий
                object : FourImageFragment.FourImageClickListener {
                override fun onCLick() {

                    // Количество правильных ответов равно количеству вопросов
                    // На которые дали правильный ответ
                    /**
                     * count - метод листа, который возвращает количество элементов
                     * по заданному предикату
                     * В предикате мы явно преобразуем Fragment в FourImageFragment
                     * И вызываем у него аттрибут, который имеет тип Boolean и зависит от
                     * правильности ответа
                     *
                     * Следовательно countOfCurrentAnswers = количеству тех элементов(вопросов)
                     * в массиве, на которые дали правильный ответ
                     */
                    countOfCurrentAnswers =
                        tasksList.count { (it as FourImageFragment).isCorrectAnswer }

                    // Если мы ещё не дошли до последнего вопроса, мы переходим к следующему
                    if (index < result.size - 1) {
                        goToFragment(result[index+1])
                    }

                    // А если дошли...
                    else {

                        // Это строка, в которой записано
                            // сколько правильных ответов / сколько всего вопросов
                        val progress = "$countOfCurrentAnswers / $countOfAllAnswers"

                        // А это процентное соотношение правильных вопросов
                        val percent =
                            ((countOfCurrentAnswers.toDouble() / countOfAllAnswers.toDouble()) * 100).toInt()

                        Log.d("Main", "Количество правильных ответов = $countOfCurrentAnswers")

                        Log.d("Main", "Прогресс = $progress Проценты = $percent")

                        // Ну и это конечный фрагмент, в котором отрисовывается результат
                        // Но можно было бы лучше, на самом деле
                        endPage = EndPage(
                            "Ваш результат : $progress" ,
                            "Процент правильных ответов : $percent %")

                        // Переходим к данному фрагменту
                        goToFragment(endPage)
                    }

                }
            })

        }

        // Возвращаем наш лист вопросов
        return result
    }
}