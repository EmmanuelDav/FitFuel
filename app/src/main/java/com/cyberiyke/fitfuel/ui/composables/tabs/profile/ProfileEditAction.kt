package com.cyberiyke.fitfuel.ui.composables.tabs.profile

import android.net.Uri

interface ProfileEditAction {

    fun startEditing()

    fun saveEdit()

    fun updateUserName(newUserName : String)

    fun updateImageUri(imageUri: Uri?)

    fun cancelEdit()

}