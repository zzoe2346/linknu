package com.example.linknu.repository;

import com.example.linknu.Entity.TaxiPartyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiPartyUserRepository extends JpaRepository<TaxiPartyUser,Long> {

    Long countByBoardId(Long boardId);
    List<TaxiPartyUser> findByBoardId(Long boardId);

}
