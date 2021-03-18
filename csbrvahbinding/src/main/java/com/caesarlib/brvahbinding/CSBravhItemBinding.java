package com.caesarlib.brvahbinding;

public class CSBravhItemBinding {
    private int variableId;
    private int layoutRes;
    private Object headAFootData;
    private int actionId;
    private Object action;

    public CSBravhItemBinding(int variableId, int layoutRes) {
        this.variableId = variableId;
        this.layoutRes = layoutRes;
    }

    public CSBravhItemBinding(int variableId, int layoutRes, Object headAFootData) {
        this.variableId = variableId;
        this.layoutRes = layoutRes;
        this.headAFootData = headAFootData;
    }

    public CSBravhItemBinding(int variableId, int layoutRes, int actionId, Object action) {
        this.variableId = variableId;
        this.layoutRes = layoutRes;
        this.actionId = actionId;
        this.action = action;
    }

    public CSBravhItemBinding(int variableId, int layoutRes, Object headAFootData, int actionId, Object action) {
        this.variableId = variableId;
        this.layoutRes = layoutRes;
        this.headAFootData = headAFootData;
        this.actionId = actionId;
        this.action = action;
    }

    public Object getHeadAFootData() {
        return headAFootData;
    }

    public void setHeadAFootData(Object headAFootData) {
        this.headAFootData = headAFootData;
    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }
}
