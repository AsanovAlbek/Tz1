package com.example.myapplication.data.repository

interface AnswerChecker {

    /**
     * Проверка вопроса с картинкой
     * @param userAnswer - ответ пользователя
     * @param currentAnswer - правильный ответ
     */
    fun isImageAnswerCorrect(userAnswer: String, currentAnswer: String?):Boolean

    /**
     * Возвращает перевод слова из языка А на язык Б
     * @param firstLangWord - слово из языка А
     */
    fun getTranslateOnSecondLang(firstLangWord : String) : String?

    /**
     * Возвращает перевод слова из языка Б на язык А
     * @param firstLangWord - слово из языка Б
     */
    fun getTranslateOnFirstLang(secondLangWord : String) : String?

    /**
     * Функция составления уточнения к вопросу
     * Если ответ правильный, то просто поздравление
     * Если не верный, то показывается правильный ответ
     * @param userAnswer - ответ пользователя
     * @param currentAnswer - правильный ответ
     */
    fun explanationText(userAnswer: String, currentAnswer: String) : String

    /**
     * Генерация текста с правильным ответом ( Используется только в функции explanationText() )
     */
    fun incorrectAnswer(currentAnswer: String) : String
}