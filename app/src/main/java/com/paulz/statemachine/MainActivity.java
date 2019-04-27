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


    private void addButton(final BaseState state){
        Button button=new Button(this);
        button.setText(state.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Message message=new Message();
                message.obj=state.getName();
                machine.sendMessage(message);*/
//              machine.transitionTo(state);
//              machine.deferMessage(machine.obtainMessage(1));
              machine.sendMessage(machine.obtainMessage(state.getCmd()));
            }
        });
        root.addView(button,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    StateA stateA=new StateA();
    StateB stateB=new StateB();
    StateC stateC=new StateC();
    StateA_1 stateA1=new StateA_1();
    StateA_2 stateA2=new StateA_2();

    private void initStateMachine(){
        machine=new MyStateMachine("myMachine1");

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

        public final static int CMD1=1;
        public final static int CMD2=2;
        public final static int CMD3=3;
        public final static int CMD4=4;
        public final static int CMD5=5;
        public final static int CMD6=6;
        public final static int CMD7=7;

        public int getCmd(){
            return 0;
        }


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
//            showToast(getName()+"---processMessage");
            if(msg.what==CMD1){
                machine.transitionTo(stateA);
                return HANDLED;
            }else if(msg.what==CMD2){
                machine.transitionTo(stateB);
                return HANDLED;
            }else if(msg.what==CMD3){
                machine.transitionTo(stateC);
                return HANDLED;
            }else if(msg.what==CMD4){
                machine.transitionTo(stateA1);
                return HANDLED;
            }else if(msg.what==CMD5){
                machine.transitionTo(stateA2);
                return HANDLED;
            }
            return NOT_HANDLED;
        }
    }

    public class StateA extends BaseState {

        @Override
        public int getCmd() {
            return CMD1;
        }
    }

    public class StateA_1 extends BaseState {
        @Override
        public int getCmd() {
            return CMD4;
        }

    }

    public class StateA_2 extends BaseState {
        @Override
        public int getCmd() {
            return CMD5;
        }

    }

    public class StateB extends BaseState {
        @Override
        public int getCmd() {
            return CMD2;
        }

    }

    public class StateC extends BaseState {
        @Override
        public int getCmd() {
            return CMD3;
        }

    }
}
