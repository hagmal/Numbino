package com.example.numbino_s305896.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = OransjeHovedfarge,
    onPrimary = OnOransje,

    secondary = BlaaSekundaer,
    onSecondary = OnBlaa,

    tertiary = TurkisTertiaer,
    onTertiary = OnTurkis,

    background = Bakgrunn,
    onBackground = OnBakgrunn,

    surfaceVariant = SvarBoks,
    onSurfaceVariant = onSvarBoks

)


@Composable
fun Numbino_s305896Theme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    MaterialTheme (
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}