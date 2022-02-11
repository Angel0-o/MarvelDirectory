package com.example.marveldirectory.model.data

data class MarvelResponse(val code:Int = 999,
                          val status:String = "default",
                          val copyright:String = "default",
                          val attributionText:String = "default",
                          val attributionHTML:String = "default",
                          val etag:String = "default",
                          val data:DataResponse = DataResponse()
                          )
