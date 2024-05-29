package com.board.boardTest.repository;

import com.board.entity.Board;
import com.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.lang.module.ResolutionException;
import java.util.List;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void boardInsert() {

        for (int i = 0; i < 10; i++) {
            Board board = Board.builder()
                    .title("제목")
                    .name("재현이")
                    .content("내용")
                    .build();
            boardRepository.save(board);
        }
    }

    @Test
    public void boardGetOne(){

        Board board = boardRepository.findById(2L)
                        .orElseThrow(() -> new EntityNotFoundException("Board Not Found"));

        log.info(board);

    }
    @Test
    public void boardGetAll(){
        List<Board> boards = boardRepository.findAll();

        boards.forEach(list -> log.info(list));
    }

    @Test
    public void boardDelete(){
        Board board = boardRepository.getOne(1L);
        Long board_id = 1L;
        boardRepository.delete(board);
    }

    @Test
    public void boardUpdate(){
        Board board = Board.builder()
                .id(1L)
                .name("꺄미")
                .title("제목수정")
                .content("내용 수정")
                .build();

        boardRepository.save(board);
    }
}
