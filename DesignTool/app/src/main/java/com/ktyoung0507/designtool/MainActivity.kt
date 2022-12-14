package com.ktyoung0507.designtool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var child = Child()
        child.callVariables()

        var parent = Parent()
        Log.d("Visibility", "Parent : protected 변수의 값은 ${parent.defaultVal}")
        Log.d("Visibility", "Parent : protected 변수의 값은 ${parent.internalVal}")
    }
}

open class Parent {
    private val privateVal = 1
    protected open val protectedVal = 2
    internal val internalVal = 3
    val defaultVal = 4
}

class Child : Parent() {
    fun callVariables() {
        Log.d("Visibility", "Child : protected 변수의 값은 ${protectedVal}")
        Log.d("Visibility", "Child : internal 변수의 값은 ${internalVal}")
        Log.d("Visibility", "Child : 기본제한자 변수 defaultVal의 값은 ${defaultVal}")
    }
}

abstract class Animal {
    fun walk() {
        Log.d("abstract", "걷습니다.")
    }
    abstract fun move()
}

class Bird : Animal() {
    override fun move() {
        Log.d("abstract", "날아서 이동합니다.")
    }
}

interface InterfaceKotlin {
    var variable : String
    fun get()
    fun set()
}

class KotlinImp1 : InterfaceKotlin {
    override var variable : String = "init value"
    override fun get() {

    }
    override fun set() {

    }
}