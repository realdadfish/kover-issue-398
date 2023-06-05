package com.example.lib

object Strings {
    fun concatenate(first: String, second: String) = "$first:$second"
    fun remove(first: String, second: String) = first.replace(second, "")
}