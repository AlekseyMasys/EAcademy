package ru.eltex.testsystem.model;

import java.util.List;

public class TestAnswers {

    public List<List<String>> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<List<String>> checkedItems) {
        this.checkedItems = checkedItems;
    }

    @Override
    public String toString() {
        return "TestAnswers{" +
                "checkedItems=" + checkedItems +
                '}';
    }

    private List<List<String>> checkedItems; //

}

