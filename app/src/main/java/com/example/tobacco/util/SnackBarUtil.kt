package com.example.tobacco.util

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

class SnackBarUtil {

    companion object{
        // 带按钮的Snackbar函数
        suspend fun showMySnackbar(
            snackbarHostState: SnackbarHostState,
            message: String,
            actionLabel: String? = null,
            duration: SnackbarDuration = SnackbarDuration.Short
        ) {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                duration = duration
            )
        }
    }
}