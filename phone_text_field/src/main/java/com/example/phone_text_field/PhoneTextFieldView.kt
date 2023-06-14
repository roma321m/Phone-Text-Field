package com.example.phone_text_field

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.phone_text_field.theme.EndTextShapes
import com.example.phone_text_field.theme.StartTextShapes


@Composable
fun PhoneTextFieldView(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    val viewModel: PhoneTextFieldViewModel = PhoneTextFieldViewModelFactory.getInstance().phoneTextFieldViewModel

    MyPhoneTextFieldView(
        modifier, value, onValueChange, viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPhoneTextFieldView(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    viewModel: PhoneTextFieldViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.handle(PhoneTextFieldEvent.GetCountryList)
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .decoderFactory(SvgDecoder.Factory())
            .data(viewModel.selectedCountry.flagUrl)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            modifier = Modifier
                .weight(0.4f),
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = viewModel.selectedCountry.dialCode ?: "",
                textStyle = TextStyle(textAlign = TextAlign.End),
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                shape = StartTextShapes.small as RoundedCornerShape,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(35.dp),
                        painter = painter,
                        contentDescription = null
                    )
                },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                viewModel.countryList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.dialCode ?: "",
                                textAlign = TextAlign.Center
                            )
                        },
                        leadingIcon = {
                            Image(
                                modifier = Modifier.size(35.dp),
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .decoderFactory(SvgDecoder.Factory())
                                        .data(item.flagUrl)
                                        .size(Size.ORIGINAL) // Set the target size to load the image at.
                                        .build()
                                ),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            viewModel.handle(PhoneTextFieldEvent.UpdateSelectedCountry(item))
                            onValueChange("")
                            expanded = false
                        }
                    )
                }
            }
        }

        TextField(
            modifier = Modifier
                .weight(0.5f),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = viewModel.selectedCountry.hint ?: "")
            },
            singleLine = true,
            shape = EndTextShapes.small as RoundedCornerShape,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
    }
}


