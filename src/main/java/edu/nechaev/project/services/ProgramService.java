package edu.nechaev.project.services;

import edu.nechaev.project.dto.Program;
import edu.nechaev.project.dto.Training;
import edu.nechaev.project.dto.TrainingsTrainer;
import edu.nechaev.project.repositories.ProgramRepository;
import edu.nechaev.project.repositories.TrainingRepository;
import edu.nechaev.project.repositories.TrainingsTrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingsTrainerRepository trainingsTrainerRepository;

    public Iterable<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(int id) {
        Program program = programRepository.findById(id).orElse(null);
        if (program == null) {
            throw new IllegalArgumentException("Не существует программы тренировок с id " + id);
        }
        return program;
    }

    public Program addProgram(Program program) {
        return programRepository.save(program);
    }

    public Iterable<TrainingsTrainer> getTrainings() {
        return trainingsTrainerRepository.findAll();
    }
    public Training addTraining(Training training) {
        String[] minSec = training.getBeg().split(":");
//        LocalTime beginning = LocalTime.of(Integer.parseInt(minSec[0]), Integer.parseInt(minSec[1]));

        String[] minSecEnd = training.getEnding().split(":");
//        LocalTime end = LocalTime.of(Integer.parseInt(minSecEnd[0]), Integer.parseInt(minSecEnd[1]));

        Time beginning = new Time(Integer.parseInt(minSec[0]), Integer.parseInt(minSec[1]), 0);
        Time end = new Time(Integer.parseInt(minSecEnd[0]), Integer.parseInt(minSecEnd[1]), 0);

        training.setBeginning(beginning);
        training.setEnd(end);

//        if (beginning.isAfter(end)) {
//            throw new RuntimeException("Некорректные данные");
//        }

        if (beginning.after(end)) throw new RuntimeException("Некорректные данные");

//        Training training1 = trainingRepository.findBetween(training.getTrainer(), training.getDate(), beginning, end);
//        if (training1 != null) {
//            throw new RuntimeException("Тренер занят");
//        }

        return trainingRepository.save(training);
    }
}