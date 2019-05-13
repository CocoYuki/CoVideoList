package com.yirong.videolist.base

//
// Created by YIRONG on 2019/4/29.
//
interface IPresenter <in T:IBaseView>{

  fun attachView(mRootView: T)
  fun detachView()

}