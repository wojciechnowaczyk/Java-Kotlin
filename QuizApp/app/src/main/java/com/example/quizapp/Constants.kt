package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestion(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "What is the name of this author?", R.drawable.sienkiewicz,
            "Henryk Sienkiewicz", "Bolesław Prus", "Zygmunt Krasiński",
            "Adam Mickiewicz", 1
        )
        questionsList.add(que1)

        val que2 = Question(
            1, "What is the name of this author?", R.drawable.prus,
            "Henryk Sienkiewicz", "Bolesław Prus", "Zygmunt Krasiński",
            "Adam Mickiewicz", 2
        )
        questionsList.add(que2)

        val que3 = Question(
            1, "What is the name of this author?", R.drawable.karsinski,
            "Henryk Sienkiewicz", "Bolesław Prus", "Zygmunt Krasiński",
            "Adam Mickiewicz", 3
        )
        questionsList.add(que3)

        return questionsList
    }
}