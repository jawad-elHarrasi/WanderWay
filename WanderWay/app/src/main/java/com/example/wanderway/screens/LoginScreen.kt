package com.example.wanderway.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wanderway.R
import com.example.wanderway.ui.theme.WanderWayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    val backgroundColor = MaterialTheme.colorScheme.background
    val wanderWayLogo = if (!isSystemInDarkTheme()){
        R.drawable.wanderwaylogo
    } else {
        R.drawable.wanderwaylogoblack
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = wanderWayLogo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            label = { Text("Enter your email to connect") },
            placeholder = { Text("example@domain.com") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            isError = !isEmailValid,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background, // Fond en mode clair/sombre
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground, // Texte de l'email
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground, // Texte de l'email
                cursorColor = MaterialTheme.colorScheme.primary,  // Couleur du curseur
                errorCursorColor = MaterialTheme.colorScheme.error,  // Couleur du curseur en cas d'erreur
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),  // Placeholder
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),  // Placeholder
                errorTextColor = MaterialTheme.colorScheme.error  // Texte en cas d'erreur
            )

        )

        if (!isEmailValid) {
            Text(
                text = "Invalid email address",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isValidEmail(email)) {
                    onLoginSuccess() // Naviguer vers la page suivante si l'email est valide
                } else {
                    isEmailValid = false // Afficher le message d'erreur
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Login")
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return email.matches(emailRegex.toRegex())
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    WanderWayTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
