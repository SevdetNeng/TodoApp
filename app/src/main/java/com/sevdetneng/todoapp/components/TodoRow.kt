package com.sevdetneng.todoapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sevdetneng.todoapp.model.Todo

@Composable
fun TodoRow(todo : Todo){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
    backgroundColor = Color.DarkGray) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()){
                Row(){
                    Text(todo.title)
                    Spacer(Modifier.width(8.dp))
                    Checkbox(checked = false, onCheckedChange = { } , modifier =
                    Modifier.size(25.dp))
                }
                
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Todo")
            }
            Spacer(Modifier.height(8.dp))
            Text(todo.description)
        }
    }
}