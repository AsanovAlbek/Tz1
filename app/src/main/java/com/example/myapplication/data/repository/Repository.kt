package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ImagesQuestion

interface Repository {
    /**
     * Функция для подбора рандомных вариантов ответов
     */
    fun getRandomAnswers(question: String) : MutableList<ImagesQuestion>
}