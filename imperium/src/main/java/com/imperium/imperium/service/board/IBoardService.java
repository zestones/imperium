package com.imperium.imperium.service.board;

import java.util.ArrayList;

import com.imperium.imperium.model.Board;

public interface IBoardService {

    void save(Board p);

    ArrayList<Board> findBoardsByProjectId(Long id);

    Board findBoardById(Long id);
}