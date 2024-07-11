package com.example.todoapp.components.homeScreen

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


enum class MultiSelectorOption {
    Option,
    Background,
}

@Composable
fun CustomToggle(
    viewModel: HomeViewModel,
    options: List<String>,
    onOptionSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    state: CustomToggleState = rememberMultiSelectorState(
        options = options,
        selectedOption = viewModel.option.value
    ),
) {
    LaunchedEffect(key1 = options, key2 = viewModel.option.value) {
        state.selectOption(this, options.indexOf(viewModel.option.value))
    }
    Layout(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(shape = RoundedCornerShape(percent = 50))
            .background(MaterialTheme.colorScheme.surface),
        content = {
            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .layoutId(MultiSelectorOption.Option)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() })
                        {onOptionSelect(option)},
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = option,
                        color = if(index == state.selectedIndex.toInt()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .layoutId(MultiSelectorOption.Background)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(percent = 100))
                        .background(MaterialTheme.colorScheme.primary),
                )
            }
        }
    ) { measurables, constraints ->
        val optionWidth = constraints.maxWidth / options.size
        val optionConstraints = Constraints.fixed(
            width = optionWidth,
            height = constraints.maxHeight,
        )
        val optionPlaceables = measurables
            .filter { measurable -> measurable.layoutId == MultiSelectorOption.Option }
            .map { measurable -> measurable.measure(optionConstraints) }
        val backgroundPlaceable = measurables
            .first { measurable -> measurable.layoutId == MultiSelectorOption.Background }
            .measure(optionConstraints)
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            backgroundPlaceable.placeRelative(
                x = (state.selectedIndex * optionWidth).toInt(),
                y = 0,
            )
            optionPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = optionWidth * index,
                    y = 0,
                )
            }
        }
    }
}

@Stable
interface CustomToggleState {
    val selectedIndex: Float

    fun selectOption(scope: CoroutineScope, index: Int)
}
@Stable
class CustomToggleStateImpl(
    options: List<String>,
    selectedOption: String,
) : CustomToggleState {
    override val selectedIndex: Float
        get() = _selectedIndex.value
    private var _selectedIndex = Animatable(options.indexOf(selectedOption).toFloat())
    private val AnimationDurationMillis = 200
    private val animationSpec = tween<Float>(
        durationMillis = AnimationDurationMillis,
        easing = FastOutSlowInEasing,
    )

    override fun selectOption(scope: CoroutineScope, index: Int) {
        scope.launch {
            _selectedIndex.animateTo(
                targetValue = index.toFloat(),
                animationSpec = animationSpec,
            )
        }
    }
}

@Composable
fun rememberMultiSelectorState(
    options: List<String>,
    selectedOption: String,
) = remember {
    CustomToggleStateImpl(
        options,
        selectedOption,
    )
}

