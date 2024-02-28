package com.example.caasworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.caasworkshop.ui.theme.CaaSWorkshopTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Retrieves the viewmodel initialized by Koin.
        val mainViewModel: MainViewModel by viewModel()
        setContent {
            //This is a state variable. Whenever the value of catImage changes within the ViewModel, this value will change as a result AND trigger a UI change if it's used in a view.
            val catImage = mainViewModel.catImage.observeAsState().value
            DisplayCatImage(catImage, mainViewModel)
        }
    }
}

@Composable
fun DisplayCatImage(catImage: ApiService.CatImage?, viewModel: MainViewModel) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (catImage != null) {
            AsyncImage(model = catImage.url, contentDescription = "Pic of cat", modifier = Modifier
                .fillMaxWidth()
                .height(300.dp))

            Button(onClick = {
             viewModel.fetchRandomCatImage()
            }) {
                Text("Show a new picture")
            }
        }
        
    }
}
