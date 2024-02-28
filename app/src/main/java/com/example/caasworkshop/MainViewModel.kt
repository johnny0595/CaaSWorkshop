package com.example.caasworkshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caasworkshop.ApiService
import kotlinx.coroutines.launch

class MainViewModel(private val catApiService: ApiService) : ViewModel() {

    // Can be changed within the ViewModel. For example, you can call _catImage.postValue(newCatImage) to update the value.
    private val _catImage = MutableLiveData<ApiService.CatImage>()
    // Exposes the data to the outside, allowing UI components to observe changes. UI components cannot modify this data directly, which preserves the integrity of the ViewModel's state.
    val catImage: LiveData<ApiService.CatImage> = _catImage

    init {
        //Whenever Koin initializes this class, go ahead and fetch an image to show to th euser.
        fetchRandomCatImage()
    }

    public fun fetchRandomCatImage() {
        /*
            A ViewModelScope is a feature of the Android Architecture Components that provides a way to launch and run coroutines within the scope of a ViewModel. This scope is tied to the ViewModel lifecycle, ensuring that all coroutines launched in this scope are automatically canceled when the ViewModel is cleared and destroyed. This helps to prevent memory leaks and ensures that your app does not perform unnecessary work.
        */
        viewModelScope.launch {
            //response will be an HTTP response class. It'll have a body, status and message and more.
            val response = catApiService.fetchRandomCatImage()
            //Response classes have fields that determine whether the request is successful, we can use that to only update the value if it was successful.
            if (response.isSuccessful) {
                //Response classes have a body, which will be of type List<CatImage> as we've defined it, so we get the first (and onltitem in that list.
                _catImage.value = response.body()?.first()
            } else {
                // Handle error
            }
        }
    }
}