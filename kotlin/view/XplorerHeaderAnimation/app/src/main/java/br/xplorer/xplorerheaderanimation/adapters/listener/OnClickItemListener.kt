package br.xplorer.xplorerheaderanimation.adapters.listener

interface OnClickItemListener<T> {
    fun onClick(data: T, pos: Int)
    fun onClick(data: T)
}