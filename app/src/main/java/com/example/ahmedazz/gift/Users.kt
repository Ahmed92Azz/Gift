package com.example.ahmedazz.gift

class Users {

    var id:String? = null
    var user:String? = null
    var radioBtnSelected:String? = null

    constructor(){

    }

    constructor(id: String, user: String, radioSelected:String){
        this.id = id
        this.user = user
        this.radioBtnSelected = radioSelected
    }



}