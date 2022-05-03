package cu.iptriana.mydog.ui.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

sealed class DateSelectorUiState<out T> {
    object Loading : DateSelectorUiState<Nothing>()
    data class Error(@StringRes val errorMessage: Int) : DateSelectorUiState<Nothing>()
}

class MainViewModel: ViewModel() {
}