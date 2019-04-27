package com.paulz.statemachine;

import android.os.Handler;
import android.os.Looper;

import com.paulz.statemachine.core.StateMachine;

/**
 * Created by Paul Z on 2019/4/26.
 * Description:
 */
public class MainPageStateMachine extends StateMachine {
    public MainPageStateMachine(String name) {
        super(name);
    }

    public MainPageStateMachine(String name, Looper looper) {
        super(name, looper);
    }

    public MainPageStateMachine(String name, Handler handler) {
        super(name, handler);
    }






}
