package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun AlertAutoCompleteTextField(
    placeholderText: String,
    listItems: List<String>,
    getInfo: (listItem: String) -> Unit
) {

    var listItem by remember {
        mutableStateOf(placeholderText)
    }

    val heightTextFields by remember {
        mutableStateOf(60.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    var isTyping by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                    isTyping = !isTyping
                }
            )
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.height(heightTextFields)
                        /*.border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )*/
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = listItem,
                    onValueChange = {item ->
                        listItem = item
                        getInfo(listItem)
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        disabledLabelColor = Color.Black,
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded
                            if(expanded){listItem = emptyString }
                        }) {
                            Icon(
                                modifier = Modifier.size(LocalSpacing.current.semiLarge),
                                imageVector = if(expanded)Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = PrimaryThemeColor
                            )
                        }
                    }
                )
            }

        Divider(modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
            color = if (isTyping) Color.Black else Color.Gray
        )

        AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp)
                        .wrapContentHeight(),
                    elevation = 15.dp,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {
                        if (listItem.isNotEmpty()) {
                            items(
                                listItems.filter {
                                    it.lowercase().contains(listItem.lowercase()) ||
                                    it.lowercase().contains("others")
                                }.sorted()
                            ) {
                                DropDownItems(title = it) { title ->
                                    listItem = title
                                    getInfo(title)
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                listItems.sorted()
                            ) {
                                DropDownItems(title = it) { title ->
                                    listItem = title
                                    getInfo(title)
                                    expanded = false
                                }
                            }
                        }

                    }
                }
            }

    }

}
