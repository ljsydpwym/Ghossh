package com.jossephus.chuchu.ui.terminal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jossephus.chuchu.ui.components.ChuButton
import com.jossephus.chuchu.ui.components.ChuButtonVariant
import com.jossephus.chuchu.ui.components.ChuText
import com.jossephus.chuchu.ui.theme.ChuColors
import com.jossephus.chuchu.ui.theme.ChuTypography

private const val ITEMS_PER_ROW = 6

@Composable
fun KeyboardAccessoryBar(
    items: List<AccessoryKeyItem>,
    modifierState: ModifierState,
    onAction: (AccessoryAction) -> Unit,
    onSettings: (() -> Unit)? = null,
    maxRows: Int = 1,
    modifier: Modifier = Modifier,
) {
    val buttonHeight = 30.dp
    val buttonPadding = PaddingValues(start = 2.dp, end = 2.dp, top = 3.dp, bottom = 3.dp)
    val typography = ChuTypography.current

    // Split items into rows of ITEMS_PER_ROW, up to maxRows
    val rows = items.chunked(ITEMS_PER_ROW).take(maxRows)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 8.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Main key rows — fill available space
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    rowItems.forEach { item ->
                        val toggleModifier = (item.action as? AccessoryAction.ToggleModifier)?.modifier
                        if (toggleModifier != null) {
                            ToggleButton(
                                label = item.label,
                                enabled = modifierState.isEnabled(toggleModifier),
                                onClick = { onAction(item.action) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(buttonHeight),
                                contentPadding = buttonPadding,
                            )
                        } else {
                            ChuButton(
                                onClick = { onAction(item.action) },
                                variant = ChuButtonVariant.Outlined,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(buttonHeight),
                                contentPadding = buttonPadding,
                            ) {
                                ChuText(item.label, style = typography.labelSmall)
                            }
                        }
                    }

                    // Fill remaining slots with spacers so all rows have equal column count
                    repeat(ITEMS_PER_ROW - rowItems.size) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .height(buttonHeight),
                        )
                    }
                }
            }
        }

        // Settings gear — always on the right, outside the weight rows
        if (onSettings != null) {
            Spacer(modifier = Modifier.width(4.dp))
            ChuButton(
                onClick = onSettings,
                variant = ChuButtonVariant.Outlined,
                modifier = Modifier.height(buttonHeight),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
            ) {
                ChuText("⚙", style = typography.labelSmall)
            }
        }
    }
}

@Composable
private fun ToggleButton(
    label: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    contentPadding: PaddingValues,
) {
    val colors = ChuColors.current
    val typography = ChuTypography.current
    val activeLabel = if (enabled) "• $label" else label
    ChuButton(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        variant = if (enabled) ChuButtonVariant.Filled else ChuButtonVariant.Outlined,
    ) {
        ChuText(
            activeLabel,
            style = typography.labelSmall,
            color = if (enabled) colors.onAccent else colors.textSecondary,
        )
    }
}
