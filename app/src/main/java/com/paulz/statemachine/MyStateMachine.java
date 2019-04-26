package com.paulz.statemachine;

import android.os.Handler;
import android.os.Looper;

import com.paulz.statemachine.core.StateMachine;

/**
 * Created by Paul Z on 2019/4/26.
 * Description:
 */
public class MyStateMachine extends StateMachine {
    public MyStateMachine(String name) {
        super(name);
    }

    public MyStateMachine(String name, Looper looper) {
        super(name, looper);
    }

    public MyStateMachine(String name, Handler handler) {
        super(name, handler);
    }
}
