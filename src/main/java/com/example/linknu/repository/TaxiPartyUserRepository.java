package com.example.linknu.repository;

import com.example.linknu.Entity.TaxiPartyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiPartyUserRepository extends JpaRepository<TaxiPartyUser,Long> {

    Long countByBoardId(Long boardId);

}
