package com.example.myapplication.presentation.adapter

import android.graphics.Color.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.ImagesQuestion

class FourImagesAdapter(
    private val fragment : Fragment,
    private val answers : List<ImagesQuestion>,
) : RecyclerView.Adapter<FourImagesAdapter.FourImagesHolder>() {

    // Позиция выбранного элемента
    private var selectedItemPosition = -1

    inner class FourImagesHolder(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {
        //Задаём переменные холдера
        // Контейнер, в котором находится слово и картинка
        val frameLayout : FrameLayout = itemView.findViewById(R.id.four_image_item_container)

        // Картинка
        val image : ImageView = itemView.findViewById(R.id.image_answer)

        // Слово
        val text : TextView = itemView.findViewById(R.id.text_answer)

        // Функция для заполнения данных
        fun binding(answer : ImagesQuestion) {
            // Если ничего не выбрано, то фон белый
            if (selectedItemPosition == - 1) {
                frameLayout.setBackgroundColor(WHITE)
            }
            else {
                // Если переменная выбранного элемента
                    // Равна кликнутому элементу в адаптере, то фон делаем зелёным
                if (selectedItemPosition == bindingAdapterPosition) {
                    frameLayout.setBackgroundColor(GREEN)
                }
                else {
                    // Если выбрали что то другое, то фон предыдущего выбранного элемента
                        // Снова белый
                    frameLayout.setBackgroundColor(WHITE)
                }
            }

            Log.d("Adapter",
                selectedItemPosition.toString() + " and " + bindingAdapterPosition)

            text.text = answer.secondLangWord
            image.setImageResource(answer.image)

            // Обработчик нажатий на элемент адаптера
            itemView.setOnClickListener {
                // Если переменная выбранного элемента не равна кликнутому элементу
                if (selectedItemPosition != bindingAdapterPosition) {
                    // Уведомляем адаптер, что выбранный элемент поменялся
                    notifyItemChanged(selectedItemPosition)
                    // Присваиваем выбранному элементу кликнутый элемент
                    selectedItemPosition = bindingAdapterPosition
                    notifyItemChanged(selectedItemPosition)
                }
            }

        }
    }

    // Возвращаем выбранный элемент
    // p.s. На самом деле я неудачно назвал класс ImageQuestion, это скорее ответ, чем вопрос
    fun getSelectedAnswer() : ImagesQuestion? {
        if (selectedItemPosition != -1) {
            return answers[selectedItemPosition]
        }
        return null
    }

    //Метод создания холдера
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourImagesHolder {
        //Здесь мы показываем холдеру, какой у нас должен быть вариант ответа
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.four_images_item, parent, false)

        return FourImagesHolder(item)
    }

    //Биндинг данных в холдере
    override fun onBindViewHolder(holder: FourImagesHolder, position: Int) {

        //Заполняем холдер данными
        holder.binding(answers[position])
    }

    //Метод для получения количества элементов
    override fun getItemCount(): Int = answers.size

    //Класс холдер для вариантов ответов с картинками
}
