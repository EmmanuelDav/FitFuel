package com.cyberiyke.fitfuel.ui.composables.tabs.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberiyke.fitfuel.data.model.Gender
import com.cyberiyke.fitfuel.data.model.User
import com.cyberiyke.fitfuel.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(), OnBoardingScreenEvent {
    private val _user = MutableStateFlow(User())
    var user = _user.asStateFlow()


    private val _errorMessage = mutableStateOf("")
    val errorMsg: State<String> = _errorMessage

    init {

        userRepository.user.onEach { it -> _user.update { it } }.launchIn(viewModelScope)

    }

    private fun User.isUserValid(): Boolean {
        return name.isNotBlank() && weightInKG > 0
    }

    override fun updateName(name: String) {
        _user.update { it.copy(name = name) }
    }

    override fun updateGender(gender: Gender) {
        _user.update { it.copy(gender = gender) }
    }

    override fun updateWeight(weightInKg: Float) {
        _user.update{ it.copy(weightInKG = weightInKg) }
    }

    override fun updateWeeklyGoal(weeklyGoalInKm: Float) {
        _user.update { it.copy(weeklyGoadInKm = weeklyGoalInKm) }
    }

    fun saveUser(navigate: () -> Unit){
       if(!user.value.isUserValid()){
       _errorMessage.value = "Enter Valid Input"
       }

        viewModelScope.launch {
userRepository.updateUser(user.value)
            navigate()
        }
    }


}