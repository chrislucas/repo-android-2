package br.xplorer.driwm.adapters.rc

interface OnItemLongClickListener<T>: OnItemClickListener<T> {
    fun onLongClick(item: T) : Boolean
}