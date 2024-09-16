package com.br.recyclerviewcomponent.model.viewholdertype


interface ViewHolderType {
    val type: Int
}

object EmptyStateViewType : ViewHolderType {
    const val EMPTY_STATE_VIEW_TYPE = 0
    override val type: Int
        get() = EMPTY_STATE_VIEW_TYPE
}

object CustomViewType : ViewHolderType {
    const val CUSTOM_VIEW_TYPE = 1
    override val type: Int
        get() = CUSTOM_VIEW_TYPE

}

object SimpleTextViewType : ViewHolderType {
    const val JUST_TEXT = 2
    override val type: Int
        get() = JUST_TEXT
}

object IconTextViewType : ViewHolderType {
    const val ICON_TEXT = 3
    override val type: Int
        get() = ICON_TEXT
}

