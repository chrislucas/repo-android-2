package com.br.experience.funmobdatascience.views.list.action

interface ProvideItemViewType<T> {
    fun provide(data: T?): Int
}