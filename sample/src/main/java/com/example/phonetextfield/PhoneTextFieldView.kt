package com.example.phonetextfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.phonetextfield.ui.theme.EndTextShapes
import com.example.phonetextfield.ui.theme.StartTextShapes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextFieldView(
    value: String,
    onValueChange: (String) -> Unit,
    onSelect: (select: String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    viewModel: PhoneTextFieldViewModel = hiltViewModel(),
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("+595") }
    val colors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .weight(0.4f),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier,
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                shape = StartTextShapes.small as RoundedCornerShape,
                leadingIcon = leadingIcon,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
               // colors = colors
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                viewModel.areaCodeList.value.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            onSelect(selectedText)
                        }
                    )
                }
            }
        }

        TextField(
            modifier = Modifier
                .weight(0.5f),
            value = value,
            onValueChange = { number ->
                //TODO add checkes
                onValueChange(number)
            },
            singleLine = true,
            shape = EndTextShapes.small as RoundedCornerShape,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            //colors = colors
        )
    }
}

