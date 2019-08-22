package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.model.Subject;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubject(String id) {
        return subjectRepository.findById(id).get();
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }
}
