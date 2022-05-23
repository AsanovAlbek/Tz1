package com.example.myapplication.presentation.fragment

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.DBSimulate
import com.example.myapplication.data.repository.RepositoryImpl
import com.example.myapplication.presentation.adapter.FourImagesAdapter
import de.hdodenhof.circleimageview.CircleImageView

class FourImageFragment(
    val questionNumber : Int,
    val allQuestionsCount : Int,
    private val listener : FourImageClickListener
) : Fragment() {

    private lateinit var toolbar : Toolbar
    private lateinit var avatar : CircleImageView
    private lateinit var userNameView : TextView
    private lateinit var currencyLinLayout : LinearLayout
    private lateinit var coinsCountView : TextView
    private lateinit var coinsCurrencyView: TextView
    private lateinit var level : TextView
    private lateinit var lesson : TextView
    private lateinit var taskType : TextView
    private lateinit var task : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var closeButton : CircleImageView
    private lateinit var checkButton: Button

    var answerCounter : TextView? = null

    var isCorrectAnswer = false

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Находим фрагмент из ресурсов
        val view : View = inflater.inflate(R.layout.four_images_fragment,null)

        // Находим все view фрагмента
        toolbar = view.findViewById(R.id.toolbar)
        avatar = view.findViewById(R.id.avatar)
        userNameView = view.findViewById(R.id.user_name)
        currencyLinLayout = view.findViewById(R.id.currencyLL)
        coinsCountView = view.findViewById(R.id.coins_count)
        coinsCurrencyView = view.findViewById(R.id.coin_currency)
        level = view.findViewById(R.id.level)
        lesson = view.findViewById(R.id.lesson)
        taskType = view.findViewById(R.id.task_type)
        task = view.findViewById(R.id.task)

        recyclerView = view.findViewById(R.id.four_item_recycler)
        closeButton = view.findViewById(R.id.krestik)
        checkButton = view.findViewById(R.id.check_button)

        answerCounter = view.findViewById(R.id.answer_counter)

        answerCounter?.text = "$questionNumber/$allQuestionsCount"

        // Добавление меню для нашего тулбара
        toolbar.inflateMenu(R.menu.toolbar_menu)

        // Обработка клика тулбара
        toolbar.setOnMenuItemClickListener {
            Toast.makeText(activity, "Нажат пункт ${it.title}", Toast.LENGTH_SHORT).show()
            true
        }



        // Берём случайный вопрос для задания
        task.text = DBSimulate.getRandomImageQuestion()

        // Определяем для него правильный ответ
        val currentAnswer =
            RepositoryImpl.getTranslateOnSecondLang(task.text.toString())

        // Подбираем 4 варианта ответов : 3 случайные и один правильный, они перемешаны
        val randomAnswers = RepositoryImpl.getRandomAnswers(task.text.toString())

        // Настройки адаптера
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val adapter = FourImagesAdapter(this, randomAnswers)
        recyclerView.adapter = adapter

        // Действие при нажатии на крестик
        closeButton.setOnClickListener{
            Toast.makeText(activity, "Закрытие задания", Toast.LENGTH_SHORT).show()
        }

        // Создаём строитель диалога
        val dialogBuilder = AlertDialog.Builder(requireActivity())


        // Если пользователь ничего не выбрал, то он не может нажать на кнопку
        //checkButton.isEnabled = adapter.getSelectedAnswer() != null

        // Дейсвие при нажатии на кнопку " Ответить "

        checkButton.setOnClickListener {


            // Если всё же пользователь что то выбрал,
            // то мы в диалог записываем уточнение к его ответу

            // Если есть выбранный ответ, то мы записываем в explanationText
            // Пояснение к вопросу, которое передадим диалогу, а так же выведем в лог


            if (adapter.getSelectedAnswer() != null) {

                val userAnswer = adapter.getSelectedAnswer()!!.secondLangWord

                val explanation = RepositoryImpl.explanationText(
                    userAnswer, currentAnswer
                )

                isCorrectAnswer = userAnswer.compareTo(currentAnswer) == 0

                Log.d("Fragment", "Ответ пользователя = $userAnswer" +
                        " Правильный ответ $currentAnswer" +
                        " Правильный ли $isCorrectAnswer")

                // Задаём строителю диалога параметры
                dialogBuilder.setMessage(explanation).
                    setNegativeButton(
                        "Продолжить",
                            DialogInterface.OnClickListener {
                                    dialog, _ ->
                                listener.onCLick()
                                dialog.dismiss()
                    })

                val alertDialog = dialogBuilder.create()
                alertDialog.show()

            }
        }

        return view
    }

    interface FourImageClickListener {
        fun onCLick()
    }

}