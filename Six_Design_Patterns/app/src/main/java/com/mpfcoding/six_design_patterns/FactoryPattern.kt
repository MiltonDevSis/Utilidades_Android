package com.mpfcoding.six_design_patterns

enum class Dialogtype {
    DIALOG_CREATE_CHAT,
    DIALOG_DELETE_MESSAGE,
    DIALOG_EDIT_MESSAGE
}

sealed class Dialog {
    object CreateChatDialog : Dialog()
    object DeleteMessageDialog : Dialog()
    object EditMessageDialog : Dialog()
}

object DialogFactory {

    fun createDialog(dialog: Dialogtype): Dialog {
        return when (dialog) {
            Dialogtype.DIALOG_CREATE_CHAT -> Dialog.CreateChatDialog
            Dialogtype.DIALOG_DELETE_MESSAGE -> Dialog.DeleteMessageDialog
            Dialogtype.DIALOG_EDIT_MESSAGE -> Dialog.EditMessageDialog
        }
    }
}