package ui.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.clipEntry
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.composables.AttributedString
import ui.composables.FooterView
import ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(darkTheme: Boolean, dynamicColor: Boolean, viewModel: AppViewModel) {
    AppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        viewModel.handlePasswordIntent(PasswordIntent.LaunchApp)

        val clipboardManager = LocalClipboard.current

        var isAnimating by remember { mutableStateOf(false) }
        var length by remember { mutableFloatStateOf(20f) }
        var textState by remember { mutableStateOf("") }

        val coroutineScope = rememberCoroutineScope()
        val interactionSource = remember { MutableInteractionSource() }

        LaunchedEffect(Unit) {
            textState = viewModel.getRandomString(length = length.toInt())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isAnimating) {
                AnimatedContent(textState) {
                    Text(AttributedString(it), textAlign = TextAlign.Center)
                }
            }
            else {
                Text(AttributedString(textState), textAlign = TextAlign.Center)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Button(onClick = { textState = viewModel.getRandomString(length = length.toInt()) }) {
                    Text("Generate password")
                }
                Button(onClick = {
                    coroutineScope.launch {
                        clipboardManager.setClipEntry(clipEntry(textState))
                    }
                }) {
                    Text("Copy password")
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Slider(
                        modifier = Modifier.width(200.dp),
                        value = length,
                        valueRange = 10f..25f,
                        onValueChange = {
                            isAnimating = true
                            length = it
                            textState = viewModel.getRandomString(length = length.toInt())
                        },
                        thumb = {
                            SliderDefaults.Thumb(
                                interactionSource = interactionSource,
                                modifier = Modifier.height(30.dp)
                            )
                        },
                        onValueChangeFinished = { isAnimating = false },
                    )
                    Text(
                        text = "${length.toInt()}",
                        color = Color.Gray,
                        fontSize = 8.sp,
                        lineHeight = 1.sp
                    )
                }
                Checkboxes(
                    viewModel = viewModel,
                    onChecked = { textState = viewModel.getRandomString(length = length.toInt()) }
                )
            }
            Spacer(Modifier.weight(1f))
            FooterView()
        }
    }
}

@Composable
private fun Checkboxes(viewModel: AppViewModel, onChecked: () -> Unit) {
    val passwordState by viewModel.passwordState.collectAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {
        ReusableCheckbox(
            checked = passwordState.includeUppercase,
            onChecked = {
                viewModel.handlePasswordIntent(PasswordIntent.IncludeUppercase(includeUppercase = it))
                onChecked()
            },
            hintText = "Uppercase"
        )
        ReusableCheckbox(
            checked = passwordState.includeNumbers,
            onChecked = {
                viewModel.handlePasswordIntent(PasswordIntent.IncludeNumbers(includeNumbers = it))
                onChecked()
            },
            hintText = "Numbers"
        )
        ReusableCheckbox(
            checked = passwordState.includeSymbols,
            onChecked = {
                viewModel.handlePasswordIntent(PasswordIntent.IncludeSymbols(includeSymbols = it))
                onChecked()
            },
            hintText = "Symbols"
        )
    }
}

@Composable
private fun ReusableCheckbox(checked: Boolean, onChecked: ((Boolean) -> Unit)?, hintText: String) {
    Checkbox(
        checked = checked,
        onCheckedChange = { if (onChecked != null) { onChecked(it) } }
    )
    Text(
        text = hintText,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
        lineHeight = 1.sp
    )
}
