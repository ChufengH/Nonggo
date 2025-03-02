package com.example.nonggo.contract;

import com.example.nonggo.base.IBaseView;

public interface LoginContract   {
     interface View extends IBaseView {
       void loginSuccess();
       void loginFail();
   }
}
