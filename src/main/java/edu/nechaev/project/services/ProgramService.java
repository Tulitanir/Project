package edu.nechaev.project.services;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.repositories.MemberTrainingRepository;
import edu.nechaev.project.repositories.ProgramRepository;
import edu.nechaev.project.repositories.TrainingRepository;
import edu.nechaev.project.repositories.TrainingsTrainerRepository;
import edu.nechaev.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingsTrainerRepository trainingsTrainerRepository;
    private final MemberService memberService;
    private final MemberTrainingRepository memberTrainingRepository;

    public Iterable<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(long id) {
        Program program = programRepository.findById(id).orElse(null);
        if (program == null) {
            throw new IllegalArgumentException("Не существует программы тренировок с id " + id);
        }
        return program;
    }

    public Program addProgram(Program program) {
        return programRepository.save(program);
    }

    public boolean signUp(long memberId, long trainingId) {
        Member member = memberService.findById(memberId);
        if (!Utils.isCurrentUser(member.getEmail())) {
            throw new RuntimeException("Доступ запрещён");
        }

        TrainingsTrainer training = trainingsTrainerRepository.findById(trainingId).orElseThrow(() -> new RuntimeException("Такой групповой тренировки не существует"));
        if (member.getExpirationDate() == null || member.getExpirationDate().before(training.getDate())) throw new RuntimeException("У нет абонемента");
        if (training.getMemberCount() >= training.getCapacity()) throw  new RuntimeException("Места закончились");
        MemberTraining memberTraining = memberTrainingRepository.findByTrainingAndMember(trainingId, memberId).orElse(null);
        if (memberTraining != null) throw new RuntimeException("Вы уже записаны на эту групповую тренировку");
        memberTrainingRepository.save(MemberTraining.builder()
                .member(memberId)
                .training(trainingId)
                .build());
        return true;
    }

    public Iterable<TrainingsTrainer> getTrainings() {
        return trainingsTrainerRepository.findAllActual(new Date(System.currentTimeMillis()));
    }

    public Iterable<TrainingsTrainer> trainingsTrainers(long id) {
        return trainingsTrainerRepository.findAllByTrainer(id, new Date(System.currentTimeMillis()));
    }

    public Iterable<TrainingsTrainer> findByMember(long id) {
        return trainingsTrainerRepository.findAllByMember(id, new Date(System.currentTimeMillis()));
    }

    public Training addTraining(Training training) {
        if (training.getDate().before(new java.util.Date(System.currentTimeMillis()))) throw new RuntimeException("Некорректные данные");
        String[] minSec = training.getBeg().split(":");

        String[] minSecEnd = training.getEnding().split(":");

        Time beginning = new Time(Integer.parseInt(minSec[0]), Integer.parseInt(minSec[1]), 0);
        Time end = new Time(Integer.parseInt(minSecEnd[0]), Integer.parseInt(minSecEnd[1]), 0);

        training.setBeginning(beginning);
        training.setEnd(end);


        if (beginning.after(end)) throw new RuntimeException("Некорректные данные");

        List<Training> training1 = trainingRepository.findBetween(training.getTrainer(), training.getDate().toLocalDate(), beginning.toLocalTime(), end.toLocalTime());
        if (!training1.isEmpty()) {
            throw new RuntimeException("Тренер занят");
        }

        return trainingRepository.save(training);
    }
}