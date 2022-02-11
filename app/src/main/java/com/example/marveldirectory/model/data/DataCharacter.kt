package com.example.marveldirectory.model.data

import java.io.Serializable

data class DataCharacter (val id:Int = 9,
                          val name:String = "default",
                          val description:String = "default",
                          val modified:String = "default",
                          val thumbnail:CharacterImage = CharacterImage(),
                          ) : Serializable
