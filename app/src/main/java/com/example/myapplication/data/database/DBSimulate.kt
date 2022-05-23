package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.data.contains.QuestionType
import com.example.myapplication.data.model.ImagesQuestion
import kotlin.random.Random
import kotlin.random.nextInt

object DBSimulate {

    //Обычные переводы слов (Пока не используются)

    val translateMap = hashMapOf(
        //0
        "Нэ" to "Глаз",
        //1
        "Щхьэ" to "Голова",
        //2
        "Шхэн" to "Кушать",
        //3
        "Адакъэ" to "Петух",
        //4
        "Хьэ" to "Собака",
        //5
        "Джэду" to "Кот",
        //6
        "Шы" to "Конь",
        //7
        "Тхылъ" to "Книга",
        //8
        "Мазэ" to "Луна"
    )


    //Так же переводы слов, но вместе с соответствующими картинками

    val imageQuestionsList = mutableListOf(
        ImagesQuestion(0, "Мазэ", "Луна",
            R.drawable.moon, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(1,"Мыӏэрысэ" , "Яблоко",
            R.drawable.apple, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(2, "Шхэн", "Кушать",
            R.drawable.eating1, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(3, "Адакъэ", "Петух",
            R.drawable.petuh1, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(4, "Джэду", "Кот",
            R.drawable.cat1, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(5, "Шы", "Конь",
            R.drawable.horse1, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(6, "Тхылъ", "Книга",
            R.drawable.book, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(7, "Дзыгъуэ", "Мышь",
            R.drawable.mouse1, QuestionType.IMAGE_QUESTION),
        ImagesQuestion(8, "Хьэ", "Собака",
            R.drawable.dog1, QuestionType.IMAGE_QUESTION)
    )

    /**
     * Функция, возвращающая рандомно выбранный вопрос с картинкой из массива
     */

    fun getRandomImageQuestion() : String =
        imageQuestionsList[Random.nextInt(imageQuestionsList.indices)].firstLangWord


    //Слова - поздравления для правильного ответа
    val goodWords = listOf(
        "Прекрасно!",
        "Замечательно!",
        "Отлично!",
        "Супер!",
        "Хорошо!"
    )

}