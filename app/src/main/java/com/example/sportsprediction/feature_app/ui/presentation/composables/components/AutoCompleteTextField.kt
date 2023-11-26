package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AutoCompleteTextField(
    placeholderText: String,
    listItems: List<String>,
    keyboardType: KeyboardType,
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

        Row(modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = listItem,
                onValueChange = { item ->
                    listItem = item
                    getInfo(listItem)
                    expanded = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(LocalSpacing.current.textFieldHeight)
                    .border(
                        width = LocalSpacing.current.extraSmall,
                        color = PrimaryThemeColor,
                        shape = RoundedCornerShape(LocalSpacing.current.medium)
                    )
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                enabled = true,
                singleLine = true,
            ) { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = listItem,
                    innerTextField = innerTextField,
                    singleLine = true,
                    enabled = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MainBackgroundColor,
                        focusedIndicatorColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        disabledLabelColor = Color.Black,
                    ),
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.size(LocalSpacing.current.semiLarge)
                                .padding(LocalSpacing.current.noPadding)
                                .clickable { expanded = !expanded
                                    if(expanded){listItem = emptyString }
                                },
                            imageVector = if(expanded)Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "arrow",
                            tint = PrimaryThemeColor
                        )

                    },
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(start = LocalSpacing.current.default, end = LocalSpacing.current.noPadding, bottom = LocalSpacing.current.noPadding, top = LocalSpacing.current.noPadding), // this is how you can remove the padding
                )
            }

            /*
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(heightTextFields)
                    .border(
                        width = LocalSpacing.current.extraSmall,
                        color = PrimaryThemeColor,
                        shape = RoundedCornerShape(LocalSpacing.current.medium)
                    )
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
                    textAlign = TextAlign.Start
                ),
                trailingIcon = {
                    IconButton(modifier = Modifier.padding(LocalSpacing.current.noSpacing),
                        onClick = { expanded = !expanded
                        if(expanded){listItem = emptyString }
                    }) {
                        Icon(
                            modifier = Modifier.size(LocalSpacing.current.semiLarge)
                                .padding(LocalSpacing.current.noSpacing),
                            imageVector = if(expanded)Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "arrow",
                            tint = PrimaryThemeColor
                        )
                    }
                }
            )
            */

        }

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
