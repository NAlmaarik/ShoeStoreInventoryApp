package com.example.shoestoreinventoryapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesListViewModel : ViewModel() {

    val _shoeName = MutableLiveData<String>()
    val _shoeCompany = MutableLiveData<String>()
    val _shoeSize = MutableLiveData<String>()
    val _shoeDescription = MutableLiveData<String>()

    private val _shoesList = MutableLiveData<List<ShoeObject>>()
     val shoeList : LiveData<List<ShoeObject>> = _shoesList

    private var listOfShoes = arrayListOf(ShoeObject("Sandals","Addidas","38","Comfy and Dail use"),
        ShoeObject("Sandals","Addidas","39","Comfy and Dail use"),
        ShoeObject("Sandals","Addidas","40","Comfy and Dail use"),
        ShoeObject("trainers","Nike","38","Best for running!"),
        ShoeObject("trainers","Nike","39","Best for running!"),
        ShoeObject("trainers","Nike","40","Best for running!"),
        ShoeObject("heel","Dior","38","Glossy,satin-style-upper"),
        ShoeObject("heel","Dior","39","Glossy,satin-style-upper"),
        ShoeObject("heel","Dior","40","Glossy,satin-style-upper"),
        ShoeObject("flat sandals","Asos","38","Smooth faux-leather-upper"),
        ShoeObject("flat sandals","Asos","39","Smooth faux-leather-upper"),
        ShoeObject("flat sandals","Asos","40","Smooth faux-leather-upper"),
        )

    init {
        setShoeList()
    }

    fun onSaveClicked(){
        if(!_shoeName.value.isNullOrEmpty() || !_shoeCompany.value.isNullOrEmpty() || !_shoeSize.value.isNullOrEmpty() || !_shoeDescription.value.isNullOrEmpty()) {
            listOfShoes.add(
                ShoeObject(
                    _shoeName.value!!,
                    _shoeCompany.value!!,
                    _shoeSize.value!!,
                    _shoeDescription.value!!
                )
            )
            _shoesList.value = listOfShoes

        } else{
            Log.i("new Shoe","name ${_shoeName.value} ${_shoeCompany.value} ${_shoeSize.value} ${_shoeDescription.value}")
        }

    }

    private fun setShoeList(){
        _shoesList.value = listOfShoes
    }

}