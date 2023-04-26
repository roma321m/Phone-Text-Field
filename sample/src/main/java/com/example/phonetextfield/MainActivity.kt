package com.example.phonetextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.phonetextfield.ui.theme.PhoneTextFieldTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhoneTextFieldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val model = PhoneTextModel(
                        flagUrl = "https://raw.githubusercontent.com/roma321m/Phone-Text-Field/main/Flags/001-paraguay.svg",
                        code = "+595",
                        country = "paraguay",
                        hint = "(595-21) 493 918",
                        number = ""
                    )
                    var text by remember {
                        mutableStateOf("")
                    }
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .decoderFactory(SvgDecoder.Factory())
                            .data(model.flagUrl)
                            .size(Size.ORIGINAL) // Set the target size to load the image at.
                            .build()
                    )
                    PhoneTextFieldView(
                        value = text,
                        onValueChange = { number ->
                            text = number
                        },
                        onSelect = {},
                        leadingIcon = {
                            Image(
                                painter = painter,
                                modifier = Modifier.size(35.dp),
                                contentDescription = ""
                            )
                        },
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}