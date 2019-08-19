package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class QuestionModel {
    @Getter @Setter
    private String question;
    private ArrayList<String> answers;
}
