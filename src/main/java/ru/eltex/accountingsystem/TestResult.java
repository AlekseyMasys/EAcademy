package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestResult {
    String testItem;
    Integer result;

    public TestResult(String str, int i) {
        String testItem;
        Integer result;
    }
}
