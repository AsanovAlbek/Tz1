package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.DBSimulate
import com.example.myapplication.data.model.ImagesQuestion
import java.lang.StringBuilder
import kotlin.random.Random
import kotlin.random.nextInt

object RepositoryImpl : Repository, AnswerChecker {

    private const val TAG = "Logic"

    //См. описание в интерфейсе AnswerChecker
    override fun isImageAnswerCorrect(
        userAnswer: String,
        currentAnswer: String?
    ): Boolean = userAnswer.compareTo(currentAnswer.toString()) == 0

    //См. описание в интерфейсе AnswerChecker
    override fun getTranslateOnSecondLang(firstLangWord: String): String {
        for (imageQuestion in DBSimulate.imageQuestionsList) {
            if (imageQuestion.firstLangWord.compareTo(firstLangWord) == 0) {
                return imageQuestion.secondLangWord
            }
        }
        return "Ошибка"
    }

    //См. описание в интерфейсе AnswerChecker
    override fun getTranslateOnFirstLang(secondLangWord: String): String {
        var translatedWord = ""
        for (imageQuestion in DBSimulate.imageQuestionsList) {
            if (imageQuestion.secondLangWord.compareTo(secondLangWord) == 0) {
                translatedWord = imageQuestion.firstLangWord
                break
            }
        }
        return translatedWord
    }

    //См. описание в интерфейсе AnswerChecker
    override fun explanationText(userAnswer: String, currentAnswer: String): String =
        if (userAnswer.compareTo(currentAnswer) == 0)
            DBSimulate.goodWords[Random.nextInt(DBSimulate.goodWords.indices)]
        else incorrectAnswer(currentAnswer)

    //См. описание в интерфейсе AnswerChecker
    override fun incorrectAnswer(currentAnswer: String): String =
        StringBuilder().apply {
            append("Неверный ответ \n")
            append("Правильный ответ:")
            append(currentAnswer)
        }.toString()



    //См. описание в интерфейсе Repository
    override fun getRandomAnswers(question: String): MutableList<ImagesQuestion> {
        // Лист, в котором будет по итогу 4 варианта ответов
        val answerOptions = mutableListOf<ImagesQuestion>()

        //Копия листа из DBSimulator, из него в дальнейшем будут
        // удалятся те варианты ответов, которые будут добавлены в answerOptions
        val imageQuestions = DBSimulate.imageQuestionsList.toMutableList()

        for (imageQuestion in imageQuestions) {
            //Сначала ищем правильный ответ через цикл, закидываем его в answerOptions
            // и удаляем из ImageQuestions
            if (question.compareTo(imageQuestion.firstLangWord) == 0) {
                answerOptions.add(imageQuestion)
                imageQuestions.remove(imageQuestion)
                break
            }
        }

        //Подбираются индексы второго, третьего и четвёртого варианта ответов
        //подбираются рандомно, после чего элемент с тем же индексом удаляется из ImageQuestions
        // и помещаются в answerOptions

        var counter = 3
        while (counter != 0) {

            var nextIndex = 0

            do {
                nextIndex = Random.nextInt(imageQuestions.indices)
            } while (answerOptions.contains(imageQuestions[nextIndex]))

            Log.d(TAG, "Рандомный индекс = $nextIndex")

            answerOptions.add(DBSimulate
                .imageQuestionsList[nextIndex])
            Log.d(TAG, "Добавление элемента с индексом $nextIndex в answerOptions")

            imageQuestions.removeAt(nextIndex)
            Log.d(TAG, "Удаление элемента с индексом $nextIndex в imageQuestions")

            counter--
        }

        Log.d(TAG, "Содержимое answerOptions = ")
        answerOptions.forEach {
            Log.d(TAG, it.secondLangWord)
        }


        Log.d(TAG, "Содержимое imageQuestions = ")
        imageQuestions.forEach {
            Log.d(TAG, it.secondLangWord)
        }

        // метод shuffle() условно перемешивает список, что нам как раз и нужно
        // Почему то иногда повтрояются элементы
        answerOptions.shuffle()

        Log.d(TAG, "Содержимое answerOptions = ")
        answerOptions.forEach {
            Log.d(TAG, it.secondLangWord)
        }

        answerOptions.forEach {
            Log.d(TAG, it.secondLangWord)
            Log.d(TAG, "${answerOptions.size} вариантов ответа")
        }

        return answerOptions
    }
}
