package com.example.minerva.util

import androidx.annotation.DrawableRes

data class Dicas(
    val titulo : String,
    val subtitulo : String,
    @DrawableRes val logo : Int,
    @DrawableRes val background : Int
)