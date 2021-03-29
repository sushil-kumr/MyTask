package com.sushildlh.mytasks.Modal

import java.io.Serializable

class TaskResponse: Serializable {
    var pizzaData:List<MenuData> = emptyList()
    var drinkData:List<MenuData> = emptyList()
    var sushiData:List<MenuData> = emptyList()
    var discountImages:List<String> = emptyList()
    var count:Int=0
}