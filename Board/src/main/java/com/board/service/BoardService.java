package com.board.service;

import com.board.entity.Board;
import com.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
     

    private final BoardRepository boardRepository;
    
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board getOne(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("board not found"));
    }

    public void insert(Board board) {
        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> page(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
