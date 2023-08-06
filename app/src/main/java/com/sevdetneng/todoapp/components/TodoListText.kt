package com.sevdetneng.todoapp.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun TodoListText(text : String,isDone : Boolean,isExpanded : Boolean){
    Text(text, textDecoration =
    if(isDone) TextDecoration.LineThrough else TextDecoration.None,
        maxLines = if (isExpanded) 3 else 1,
        color = if(isDone) Color.LightGray else Color.Black
    )
}