package model;

import android.graphics.Path;

public class Rule {
    private Integer id;
    private Condition condition;
    private Operation operation;
    private String value;

    public Rule(Integer id, Condition condition, Operation operation, String value) {
        this.id = id;
        this.condition = condition;
        this.operation = operation;
        this.value = value;
    }

    public Rule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n"  +
                "condition: " + condition + "\n" +
                "value: " + value + "\n" +
                "operation: " + operation;
    }
}
