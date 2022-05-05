package dinh.example.uxuifinalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val count_ = MutableLiveData(0)
    var count1: LiveData<Int> = count_

    fun incCount(temp: Int) {
        // incrementing live data
        count_.value = temp
    }
    fun reset(){
        count_.value = 0
    }
}