package com.sevdetneng.todoapp.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sevdetneng.todoapp.model.Todo
import kotlin.math.exp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoRow(todo : Todo,
            onDeleteClick : (Todo) -> Unit,
            onDoneChange : (Todo) -> Unit,
            onTodoClick : (Todo) -> Unit
){
    var expandedState by remember{ mutableStateOf(false) }


    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        .combinedClickable(onClick = {expandedState = !expandedState},
        onLongClick = {onTodoClick(todo)}
        ),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
    backgroundColor = Color.White) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp), verticalArrangement = Arrangement.Center){
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(12f)){
//                    Checkbox(checked = todo.isDone, onCheckedChange = {
//                        onDoneChange(todo)
//                    })
                    RadioButton(selected = todo.isDone,
                        onClick = { onDoneChange(todo) },
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xff6096B4),
                    unselectedColor = Color(0xff6096B4 )))
                    Column(){
                        TodoListText(text = todo.title, isDone = todo.isDone, isExpanded = expandedState)
                        Spacer(Modifier.height(4.dp))
                        if(expandedState)
                            TodoListText(text = todo.description,
                                isDone = todo.isDone,
                            isExpanded = true
                            )
                    }

                }

                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                modifier = Modifier.weight(1f)
                    .clickable {
                        onDeleteClick(todo)
                    })
            }

        }
    }
}