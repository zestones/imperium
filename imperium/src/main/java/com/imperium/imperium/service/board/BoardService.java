package com.imperium.imperium.service.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperium.imperium.model.Board;
import com.imperium.imperium.repository.BoardRepository;

@Service
public class BoardService implements IBoardService {

    @Autowired
    BoardRepository bRepo;

    /********************************
     ** FIND IN DB
     ********************************/

    /**
     * @param id : Project id property
     * @return ArrayList<Board>
     */
    public ArrayList<Board> findBoardsByProjectId(Long id) {
        return bRepo.findBoardByProjectId(id);
    }

    /**
     * @param id : Board id property
     * @return Board
     */
    @Override
    public Board findBoardById(Long id) {
        return bRepo.findBoardById(id);
    }

    /********************************
     ** UPDATE DB
     ********************************/

    /**
     * @param p
     */
    @Override
    public void save(Board p) {
        bRepo.save(p);
    }
}
