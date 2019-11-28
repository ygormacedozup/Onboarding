package br.com.zup.onboarding;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.onboarding.contract.QuestionContract;
import br.com.zup.onboarding.model.Question;

public class QuestionRepository implements QuestionContract.Repository {
    private List<Question> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    public QuestionRepository() {
        setQuestions();
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    private void setQuestions() {
        answers.add("Feijão");
        answers.add("Arroz");
        answers.add("Frango");
        answers.add("Ovo");

        questions.add(new Question("Qual time você torce?", answers, 1));
        questions.add(new Question("Quem é Jesus?", answers, 2));
        questions.add(new Question("Por que o céu é azul?", answers, 3));
    }
}
