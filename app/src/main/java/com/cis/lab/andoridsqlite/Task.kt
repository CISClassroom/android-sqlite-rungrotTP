package com.cis.lab.andoridsqlite

class Task {
    var id = 0
    var taskname:String =""

    constructor(taskname:String){
        this.taskname = taskname
    }
    constructor(id:Int,taskname:String){
        this.id =id
        this.taskname = taskname
    }
}