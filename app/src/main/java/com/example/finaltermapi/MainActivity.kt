package com.example.finaltermapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopSearchBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           HomeScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSample(){
    val searchBarState= rememberSearchBarState()
    val textFieldState= rememberTextFieldState()
    val scope= rememberCoroutineScope()
    val focusManager= LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxWidth(),
    ){
        SearchBar(
            state = searchBarState,
            inputField = {
                SearchBarDefaults.InputField(
                    textFieldState = textFieldState,
                    searchBarState=searchBarState,
                    onSearch = {
                        scope.launch {
                            searchBarState.animateToCollapsed()
                            focusManager.clearFocus()
                        }
                    },
                )
            }
        )
        LazyColumn{
            items(count = 30){
                ListItem(
                    headlineContent = {
                        Text(
                            text = it.toString()
                        )
                    }
                )
            }
        }
    }
}

