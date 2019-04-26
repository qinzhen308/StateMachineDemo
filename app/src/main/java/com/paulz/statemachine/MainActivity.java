package com.paulz.statemachine;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.paulz.statemachine.core.State;
import com.paulz.statemachine.core.StateMachine;

public class MainActivity extends AppCompatActivity {
    StateMachine machine;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root=findViewById(R.id.root);
        initStateMachine();
    }


    private void addButton(final State state){
        Button button=new Button(this);
        button.setText(state.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Message message=new Message();
                message.obj=state.getName();
                machine.sendMessage(message);*/
              machine.transitionTo(state);
            }
        });
        root.addView(button,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    private void initStateMachine(){
        machine=new MyStateMachine("myMachine1");
        StateA stateA=new StateA();
        StateB stateB=new StateB();
        StateC stateC=new StateC();
        StateA_1 stateA1=new StateA_1();
        StateA_2 stateA2=new StateA_2();
        addButton(stateA);
        addButton(stateB);
        addButton(stateC);
        addButton(stateA1);
        addButton(stateA2);
        machine.addState(stateA);
        machine.addState(stateB);
        machine.addState(stateC);
        machine.addState(stateA1,stateA);
        machine.addState(stateA2,stateA);
        machine.setInitialState(stateA);
        machine.start();
    }


    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public class BaseState extends State {


        @Override
        public void enter() {
            showToast(getName()+"---enter");
        }

        @Override
        public void exit() {
            showToast(getName()+"---exit");
        }

        @Override
        public boolean processMessage(Message msg) {
            showToast(getName()+"---processMessage");
            return super.processMessage(msg);
        }
    }

    public class StateA extends BaseState {


    }

    public class StateA_1 extends BaseState {


    }

    public class StateA_2 extends BaseState {

    }

    public class StateB extends BaseState {

    }

    public class StateC extends BaseState {

    }
}
