package com.android.hireme.service.interfaces

interface INetworkServiceCreator<T> {

    fun <S> create(networkLib: T?, serviceClass: Class<S>):S?
}