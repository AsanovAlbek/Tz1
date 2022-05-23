package com.example.myapplication.data.model

import com.example.myapplication.data.contains.QuestionType

data class ImagesQuestion(
    val id : Int,

    val firstLangWord : String,

    val secondLangWord : String,

    val image : Int,

    val questionType: QuestionType = QuestionType.IMAGE_QUESTION,
) : Question {

    //Функция для проверки ответа
    override fun checkAnswer(): Boolean =
        firstLangWord.compareTo(secondLangWord) == 0

    override fun toString(): String =
        StringBuilder().apply {
            append("id=$id,")
            append(" firstLangWord='$firstLangWord',")
            append("secondLangWord='$secondLangWord',")
            append("image=$image,")
            append("questionType=$questionType")
        }.toString()

}