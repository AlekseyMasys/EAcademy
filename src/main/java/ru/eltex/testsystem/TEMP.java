package ru.eltex.testsystem;

public class TEMP {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TEMP{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    private String name;
    private String id;

}
