package ru.eltex.testsystem.dao;

import ru.eltex.testsystem.model.TestStructure;

public interface TestStructureRepositoryCustom {
    TestStructure getByName(java.lang.String name);
}
