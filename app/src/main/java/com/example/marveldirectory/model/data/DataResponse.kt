package com.example.marveldirectory.model.data

data class DataResponse (val offset:Int = 9,
                         val limit:Int = 9,
                         val total:Int = 9,
                         val count:Int = 9,
                         val results:List<DataCharacter> = emptyList()
                         )
