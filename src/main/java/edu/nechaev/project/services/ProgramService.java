package edu.nechaev.project.services;

import edu.nechaev.project.dto.Program;
import edu.nechaev.project.dto.Training;
import edu.nechaev.project.dto.TrainingsTrainer;

public interface ProgramService {
    Iterable<Program> getAll();

    Program getById(long id);

    Program addProgram(Program program);

    boolean signUp(long memberId, long trainingId);

    Iterable<TrainingsTrainer> getTrainings();

    Iterable<TrainingsTrainer> trainingsTrainers(long id);

    Iterable<TrainingsTrainer> findByMember(long id);

    Training addTraining(Training training);
}
