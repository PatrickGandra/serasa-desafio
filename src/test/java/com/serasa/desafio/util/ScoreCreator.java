package com.serasa.desafio.util;

import com.serasa.desafio.dto.ScoreDTO;
import com.serasa.desafio.model.Score;

public final class ScoreCreator {

    private ScoreCreator(){}

    public static Score createScore() {
        return new Score("Insuficiente", 0, 200);
    }

    public static ScoreDTO createScoreDTO() {
        return new ScoreDTO("Insuficiente", 0, 200);
    }
}
